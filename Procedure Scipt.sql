--add user procedure
create or replace PROCEDURE add_user(user_name IN library_user.loginid%TYPE, 
    fName IN library_user.user_first_name%TYPE,
    mName IN library_user.user_middle_name%TYPE, 
    lName IN library_user.user_last_name%TYPE, 
    user_pass IN library_user.user_password%TYPE, 
    hNo IN library_user.user_address_house_no%TYPE,
    hStreet IN library_user.user_address_street%TYPE, 
    hCity IN library_user.user_address_city%TYPE, 
    hCountry IN library_user.user_address_country%TYPE, 
    isLib IN NUMBER)
    IS
BEGIN
    INSERT INTO Library_user VALUES(user_name,fName, mName, lName, user_pass,
        hNo, hStreet, hCity, hCountry);
    IF isLib = 1 THEN
        INSERT INTO Librarian VALUES(user_name,fName, mName, lName, user_pass,
        hNo, hStreet, hCity, hCountry);
    END IF;
    INSERT INTO Patrons VALUES(user_name, 0, fName, mName, lName, user_pass,
        hNo, hStreet, hCity, hCountry, 0, 'Allowed');
END;



--change shelf procedure
create or replace PROCEDURE change_shelf(book_isbn IN book_details.isbn_number%TYPE,
    cpyNo IN book.copy_number%TYPE, newshelf IN book.shelf_id%TYPE)
IS
    oldshelf book.shelf_id%TYPE;
    max_shelf_cap shelf.shelf_capacity%TYPE;
    e_max_shelf_cap EXCEPTION;
BEGIN
    SELECT shelf_capacity INTO max_shelf_cap FROM shelf WHERE shelf_id = newshelf;
    IF max_shelf_cap = 20 THEN
        RAISE e_max_shelf_cap;
    ELSE
    SELECT shelf_id INTO oldshelf FROM book 
            WHERE book.book_isbn_number = book_isbn
                    AND book.copy_number = cpyNo
                    AND ROWNUM = 1;
    UPDATE shelf SET shelf_capacity = shelf_capacity + 1 WHERE shelf_id = oldshelf;
    UPDATE shelf SET shelf_capacity = shelf_capacity - 1 WHERE shelf_id = newshelf;
    UPDATE book SET shelf_id = newshelf 
        WHERE book.book_isbn_number = book_isbn
                AND book.copy_number = cpyNo;
    UPDATE library_transaction
        SET shelf_id = newshelf
        WHERE isbn_number = book_isbn
        AND book_copy_number = cpyNo;
    END IF;
END;


--change title procedure
create or replace PROCEDURE change_title(book_isbn IN book_details.isbn_number%TYPE, 
    new_title IN book_details.title%TYPE)
    IS
    e_no_book_exist EXCEPTION;
    book_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO book_count FROM book_details WHERE isbn_number = book_isbn;
    IF book_count = 0 THEN RAISE e_no_book_exist;
    END IF;
    UPDATE book_details SET title = new_title 
        WHERE book_details.isbn_number = book_isbn;
    UPDATE book SET book_title = new_title
        WHERE book.book_isbn_number = book_isbn;
    UPDATE library_transaction
        SET book_title = new_title
        WHERE isbn_number = book_isbn;
    UPDATE borrowed_books
        SET book_title = new_title
        WHERE book_title = new_title;
END;




--check reserve procedure
create or replace PROCEDURE check_reserve
    IS
BEGIN
    UPDATE book
        SET current_status = 'on-shelf'
        WHERE current_status = 'on-hold'
            AND SYSDATE > loan_or_hold_date + 7;
END;



--check user procedure
create or replace PROCEDURE checkUser 
    IS
    usercount NUMBER;
    e_no_user EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO usercount FROM library_user;
        IF usercount = 0 THEN
            RAISE e_no_user;
        END IF;
END;


--delete books procedure
create or replace PROCEDURE delete_books(book_isbn IN book.book_isbn_number%TYPE,
    title IN book.book_title%TYPE, cpyNo IN book.copy_number%TYPE)
    IS
    shlf_id shelf.shelf_id%TYPE;
    book_in_trans NUMBER;
    e_book_in_transaction EXCEPTION;
    book_status book.current_status%TYPE;
BEGIN
    SELECT COUNT(*) INTO book_in_trans FROM library_transaction
        WHERE isbn_number = book_isbn
            AND book_copy_number = cpyNo
            AND book_return_status <> 'returned';
    IF book_in_trans > 0 THEN RAISE e_book_in_transaction;
    END IF;
    SELECT current_status INTO book_status FROM book
        WHERE book_isbn_number = book_isbn
        AND copy_number = cpyNo;
    IF book_status = 'on-loan' OR book_status = 'on-hold' THEN
    RAISE e_book_in_transaction;
    END IF;
    SELECT shelf_id INTO shlf_id
            FROM book WHERE book.book_isbn_number = book_isbn
                            AND book.book_title = title 
                            AND book.copy_number = cpyNo
                            AND ROWNUM = 1;
    UPDATE shelf SET shelf_capacity = shelf_capacity + 1
        WHERE shelf_id = shlf_id;
    DELETE FROM book WHERE book.book_isbn_number = book_isbn 
        AND book.book_title = title AND book.copy_number = cpyNo;
END;



--delete user procedure
create or replace PROCEDURE delete_user(userID IN library_user.loginid%TYPE)
    IS
    has_books NUMBER;
    e_user_hasBooks EXCEPTION;
BEGIN
    SELECT borrowed_books_count INTO has_books FROM patrons
        WHERE loginid = userID;
    IF has_books > 0 THEN RAISE e_user_hasBooks;
    END IF;
    DELETE FROM library_user WHERE loginid = userID;
END;



--insert auto procedure
create or replace PROCEDURE insert_author(author_id IN author.author_id%TYPE, 
    fName IN author.author_first_name%TYPE, 
    mNane IN author.author_middle_name%TYPE, 
    lName IN author.author_last_name%TYPE) IS
BEGIN
    INSERT INTO author VALUES(author_id, fName, mNane, lName);
END;



--insert book details procedure
create or replace PROCEDURE insert_book_details(isbn_num IN book_details.isbn_number%TYPE, 
    bkTitle IN book_details.title%TYPE,
    pub_date IN book_details.publication_year%TYPE) IS
BEGIN
    INSERT INTO book_details(isbn_number, title, publication_year)
    VALUES(isbn_num, bkTitle, pub_date);
END;



--insert books procedure
create or replace PROCEDURE insert_books(auth_id IN book.author_author_id%TYPE, 
        book_isbn IN book.book_isbn_number%TYPE, 
        shf_id IN book.shelf_id%TYPE,
        cpyNo IN book.copy_number%TYPE) 
    IS
        book_title book.book_title%TYPE;
        auth_fName book.author_first_name%TYPE;
        auth_lName book.author_last_name%TYPE;
        max_shelf_cap shelf.shelf_capacity%TYPE;
        book_isThere NUMBER;
        e_max_shelf_cap EXCEPTION;
        exist_shelf book.shelf_id%TYPE;
        inp_shelf book.shelf_id%TYPE;
        anyrows NUMBER;
BEGIN
    SELECT COUNT(*) INTO anyrows FROM book WHERE book.book_isbn_number = book_isbn
        AND book.copy_number = cpyNo;
    IF anyrows <> 0 THEN
        SELECT shelf_id INTO exist_shelf FROM book WHERE book.book_isbn_number = book_isbn
        AND book.copy_number = cpyNo;
        inp_shelf := exist_shelf;
    ELSE
        SELECT shelf_capacity INTO max_shelf_cap FROM shelf WHERE shelf_id = shf_id;
        IF max_shelf_cap = 0 THEN
            RAISE e_max_shelf_cap;
        ELSE
            UPDATE shelf SET shelf_capacity = shelf_capacity - 1
                WHERE shelf_id = shf_id;
            END IF;
        inp_shelf := shf_id;
    END IF;
    SELECT title INTO book_title FROM book_details 
        WHERE book_details.isbn_number = book_isbn;
        SELECT author_first_name, author_last_name INTO auth_fName, auth_lName
            FROM author WHERE author.author_id = auth_id;
    INSERT INTO book (author_author_id, book_isbn_number,
            book_title, author_first_name, author_last_name,
            shelf_id, copy_number, current_status)
            VALUES(auth_id, book_isbn, book_title, auth_fName,
            auth_lName, inp_shelf, cpyNo, 'on-shelf');
END;




--insert shelf
create or replace PROCEDURE insert_shelf(shelf_id IN shelf.shelf_id%TYPE, 
    shelf_capacity IN shelf.shelf_capacity%TYPE) IS
BEGIN
    INSERT INTO shelf VALUES (shelf_id, shelf_capacity);
END;



--pay fines procedure
create or replace PROCEDURE pay_fines(patID patrons.loginid%TYPE,
    paid_fine patrons.unpaid_fines%TYPE)
IS
    userFine patrons.unpaid_fines%TYPE;
    e_no_fine EXCEPTION;
BEGIN
    SELECT unpaid_fines INTO userFine FROM patrons
        WHERE loginid = patID;
    IF userFine = 0 OR userFine IS NULL THEN
        RAISE e_no_fine;
    END IF;
    UPDATE patrons SET
        unpaid_fines = unpaid_fines - paid_fine
        WHERE loginid = patID;
END;



--reserve book procedure
create or replace PROCEDURE reserve_book(patID IN patrons.loginid%TYPE, 
    isbnNum IN book.book_isbn_number%TYPE,
    shf_ID IN book.shelf_id%TYPE,
    cpyNo IN book.copy_number%TYPE)
    IS
    pat_books patrons.borrowed_books_count%TYPE;
    book_status book.current_status%TYPE;
    e_book_on_loan EXCEPTION;
BEGIN
    SELECT current_status INTO book_status FROM book
            WHERE book_isbn_number = isbnNum
        AND copy_number = cpyNo
        AND shelf_id = shf_ID
        AND ROWNUM = 1;
    IF book_status = 'on-loan' OR book_status = 'on-hold' THEN
        RAISE e_book_on_loan;
    ELSE
        UPDATE book
            SET current_status = 'on-hold',
                loan_or_hold_date = SYSDATE
        WHERE book_isbn_number = isbnNum
            AND copy_number = cpyNo
            AND shelf_id = shf_ID;
        UPDATE patrons
            SET borrowed_books_count = borrowed_books_count +1
            WHERE loginid = patID;
        SELECT borrowed_books_count INTO pat_books FROM patrons WHERE patrons.loginid = patID;
        IF pat_books = 2 THEN
            UPDATE patrons
                SET transaction_status = 'Not Allowed'
                WHERE patrons.loginid = patID;
        END IF;
    END IF;
END;



--return book procedure

create or replace PROCEDURE return_book(patID IN library_transaction.patron_id%TYPE,
    isbn_num IN library_transaction.isbn_number%TYPE, 
    cpyNo IN library_transaction.book_copy_number%TYPE,
    libID IN library_transaction.librarian_id%TYPE
    )
    IS
        isLib NUMBER;
        noOfDays NUMBER;
        e_not_librarian EXCEPTION;
        transID library_transaction.transaction_id%TYPE;
        ret_date library_transaction.book_return_date%TYPE;
        pen_fine borrowed_books.penalty_fine%TYPE;
BEGIN
    SELECT COUNT(*) INTO isLib FROM librarian
        WHERE loginid = libID;
    IF isLib <> 0 THEN
        SELECT transaction_id INTO transID FROM library_transaction
            WHERE patron_id = patID 
                AND isbn_number = isbn_num
                AND book_copy_number = cpyno 
                AND book_return_date = (SELECT MAX(book_return_date)
                    FROM library_transaction
                    WHERE patron_id = patID 
                AND isbn_number = isbn_num
                AND book_copy_number = cpyno);
        UPDATE library_transaction
            SET book_return_date = SYSDATE,
                book_return_status = 'returned'
            WHERE transaction_id = transID;
        SELECT return_date INTO ret_date FROM borrowed_books WHERE patron_id = patID AND
                transaction_id = transID;
        IF SYSDATE > ret_date THEN
            UPDATE borrowed_books
                SET penalty_fine = (SYSDATE - return_date) *20,
                    return_date = SYSDATE
                WHERE patron_id = patID AND
                transaction_id = transID;
        END IF;
        SELECT penalty_fine INTO pen_fine FROM borrowed_books
            WHERE transaction_id = transID;
        UPDATE Patrons
            SET borrowed_books_count = borrowed_books_count - 1,
                transaction_status = 'Allowed',
                unpaid_fines = unpaid_fines + pen_fine
            WHERE loginid = patID;
        UPDATE book
            SET current_status = 'on-shelf',
                loan_or_hold_date = null
            WHERE book_isbn_number = isbn_num
                AND copy_number = cpyNo;
    ELSE
        RAISE e_not_librarian;
    END IF;
END;



--update user procedure
create or replace PROCEDURE update_user(uname IN library_user.loginid%TYPE, 
    firName IN library_user.user_first_name%TYPE,
    midName IN library_user.user_middle_name%TYPE, 
    lastName IN library_user.user_last_name%TYPE, 
    upass IN library_user.user_password%TYPE, 
    houseNo IN library_user.user_address_house_no%TYPE,
    houseStreet IN library_user.user_address_street%TYPE, 
    houseCity IN library_user.user_address_city%TYPE, 
    houseCountry IN library_user.user_address_country%TYPE)
    IS
        any_rows NUMBER;
BEGIN
    SELECT COUNT(*) INTO any_rows FROM Librarian WHERE loginid = uname;
    UPDATE library_user SET
        user_first_name = firName, 
        user_middle_name = midName,
        user_last_name = lastName, 
        user_password = upass, 
        user_address_house_no = houseNo,
        user_address_street = houseStreet, 
        user_address_city = houseCity,
        user_address_country = houseCountry
    WHERE loginid = uname;
    IF any_rows <> 0 THEN
        UPDATE librarian SET                   
        user_first_name = firName, 
        user_middle_name = midName,
        user_last_name = lastName, 
        librarian_password = upass, 
        user_address_house_no = houseNo,
        user_address_street = houseStreet, 
        user_address_city = houseCity,
        user_address_country = houseCountry
    WHERE loginid = uname;   
    END IF;
    UPDATE patrons SET                   
        user_first_name = firName, 
        user_middle_name = midName,
        user_last_name = lastName, 
        patron_password = upass, 
        user_address_house_no = houseNo,
        user_address_street = houseStreet, 
        user_address_city = houseCity,
        user_address_country = houseCountry
    WHERE loginid = uname;
END;




--withdraw book procedure
create or replace PROCEDURE withdraw_book(patID IN patrons.loginid%TYPE, 
        libId IN librarian.loginid%TYPE,
        bkTitle library_transaction.book_title%TYPE,
        shlfID IN library_transaction.shelf_id%TYPE,
        cpyNo IN book.copy_number%TYPE,
        bkStatus IN library_transaction.book_return_status%TYPE) 
    IS
        auth_id library_transaction.author_id%TYPE;
        isbn_num library_transaction.isbn_number%TYPE;
        pat_books patrons.borrowed_books_count%TYPE;
        book_status book.current_status%TYPE;
        e_patron_max_books EXCEPTION;
BEGIN
    SELECT borrowed_books_count INTO pat_books FROM patrons WHERE patrons.loginid = patID;
    IF pat_books = 2 THEN
        SELECT current_status INTO book_status FROM book
            WHERE book_title = bkTitle AND
                  shelf_id = shlfID AND
                  copy_number = cpyNo
                  AND ROWNUM = 1;
            IF book_status = 'on-hold' THEN
                SELECT author_author_id, book_isbn_number INTO auth_id, isbn_num
                FROM book WHERE book.book_title = bkTitle 
                            AND book.shelf_id = shlfID 
                            AND book.copy_number = cpyNo
                            AND ROWNUM = 1;
                INSERT INTO library_transaction VALUES(transaction_id.nextval, patID, auth_id,
                        isbn_num, bkTitle, shlfID, cpyNo, libId, bkStatus, SYSDATE, SYSDATE+7);
                 UPDATE book SET current_status = bkStatus, 
                        loan_or_hold_date = SYSDATE
                    WHERE book_isbn_number = isbn_num AND
                        shelf_id = shlfID AND
                        copy_number = cpyNo;
                INSERT INTO borrowed_books(transaction_id, patron_id, book_title, return_date) 
                    VALUES(transaction_id.currval, patID,
                            bkTitle, SYSDATE+7);
            ELSE
                RAISE e_patron_max_books;
            END IF;
    ELSE
        SELECT author_author_id, book_isbn_number INTO auth_id, isbn_num
            FROM book WHERE book.book_title = bkTitle 
                            AND book.shelf_id = shlfID 
                            AND book.copy_number = cpyNo
                            AND ROWNUM = 1;
        INSERT INTO library_transaction VALUES(transaction_id.nextval, patID, auth_id,
            isbn_num, bkTitle, shlfID, cpyNo, libId, bkStatus, SYSDATE, SYSDATE+7);
        UPDATE book SET current_status = bkStatus, 
                        loan_or_hold_date = SYSDATE
                WHERE book_isbn_number = isbn_num AND
                      shelf_id = shlfID AND
                      copy_number = cpyNo;
        UPDATE patrons SET borrowed_books_count = borrowed_books_count + 1
            WHERE patrons.loginid = patID;
        INSERT INTO borrowed_books(transaction_id, patron_id, book_title, return_date) 
            VALUES(transaction_id.currval, patID,
            bkTitle, SYSDATE+7);
        SELECT borrowed_books_count INTO pat_books FROM patrons WHERE patrons.loginid = patID;
        IF pat_books = 2 THEN
            UPDATE patrons
                SET transaction_status = 'Not Allowed'
                WHERE patrons.loginid = patID;
        END IF;
    END IF;
END;


