CREATE TABLE author (
    author_id            VARCHAR2(250) NOT NULL,
    author_first_name    VARCHAR2(250) NOT NULL,
    author_middle_name   VARCHAR2(250),
    author_last_name     VARCHAR2(250) NOT NULL
);

ALTER TABLE author ADD CONSTRAINT author_pk PRIMARY KEY ( author_id );

CREATE TABLE book (
    author_author_id    VARCHAR2(250) NOT NULL,
    book_isbn_number    NUMBER(11) NOT NULL,
    book_title          VARCHAR2(250) NOT NULL,
    author_first_name   VARCHAR2(250) NOT NULL,
    author_last_name    VARCHAR2(250) NOT NULL,
    shelf_id            VARCHAR2(250) NOT NULL
);

ALTER TABLE book
    ADD CONSTRAINT book_author_fk_pk PRIMARY KEY ( author_author_id,
                                                   book_isbn_number,
                                                   shelf_id );

CREATE TABLE book_details (
    isbn_number         NUMBER(11) NOT NULL,
    title               VARCHAR2(250) NOT NULL,
    publication_year    DATE NOT NULL,
    copy_number         NUMBER(11) NOT NULL,
    current_status      VARCHAR2(250) NOT NULL,
    loan_or_hold_date   DATE NOT NULL
);

ALTER TABLE book_details
    ADD CHECK ( current_status IN (
        'on-hold',
        'on-loan',
        'on-loan-and-on-hold',
        'on-shelf'
    ) );

ALTER TABLE book_details ADD CONSTRAINT book_pk PRIMARY KEY ( isbn_number );

CREATE TABLE borrowed_books (
    transaction_id   VARCHAR2(250) NOT NULL,
    patron_id        VARCHAR2(250) NOT NULL,
    book_title       VARCHAR2(250) NOT NULL,
    return_date      DATE NOT NULL,
    penalty_fine     NUMBER(11)
);

CREATE TABLE librarian (
    loginid                 VARCHAR2(250) NOT NULL,
    user_first_name         VARCHAR2(250) NOT NULL,
    user_middle_name        VARCHAR2(250) NOT NULL,
    user_last_name          VARCHAR2(250) NOT NULL,
    librarian_password      VARCHAR2(250) NOT NULL,
    user_address_house_no   NUMBER(11) NOT NULL,
    user_address_street     VARCHAR2(250) NOT NULL,
    user_address_city       VARCHAR2(250) NOT NULL,
    user_address_country    VARCHAR2(250) NOT NULL
);

ALTER TABLE librarian ADD CONSTRAINT librarian_pk PRIMARY KEY ( loginid );

CREATE TABLE library_user (
    loginid                 VARCHAR2(250) NOT NULL,
    user_first_name         VARCHAR2(250) NOT NULL,
    user_middle_name        VARCHAR2(250),
    user_last_name          VARCHAR2(250) NOT NULL,
    user_password           VARCHAR2(250) NOT NULL,
    user_address_house_no   NUMBER(11) NOT NULL,
    user_address_street     VARCHAR2(250) NOT NULL,
    user_address_city       VARCHAR2(250) NOT NULL,
    user_address_country    VARCHAR2(250) NOT NULL
);

ALTER TABLE library_user ADD CONSTRAINT user_pk PRIMARY KEY ( loginid );

CREATE TABLE patrons (
    loginid                 VARCHAR2(250) NOT NULL,
    unpaid_fines            NUMBER(11, 2),
    user_first_name         VARCHAR2(250) NOT NULL,
    user_middle_name        VARCHAR2(250),
    user_last_name          VARCHAR2(250) NOT NULL,
    patron_password         VARCHAR2(250) NOT NULL,
    user_address_house_no   NUMBER(11) NOT NULL,
    user_address_street     VARCHAR2(250) NOT NULL,
    user_address_city       VARCHAR2(250) NOT NULL,
    user_address_country    VARCHAR2(250) NOT NULL,
    borrowed_books_count    NUMBER(11),
    transaction_status      VARCHAR2(250) NOT NULL
);

ALTER TABLE patrons ADD CHECK ( borrowed_books_count BETWEEN 1 AND 2 );

ALTER TABLE patrons
    ADD CHECK ( transaction_status IN (
        'Allowed',
        'Not Allowed'
    ) );

ALTER TABLE patrons ADD CONSTRAINT patrons_pk PRIMARY KEY ( loginid );

CREATE TABLE shelf (
    shelf_id   VARCHAR2(250) NOT NULL,
    capacity   NUMBER(11) NOT NULL
);

ALTER TABLE shelf ADD CONSTRAINT shelf_pk PRIMARY KEY ( shelf_id );

CREATE TABLE transaction (
    transaction_id       VARCHAR2(250) NOT NULL,
    patron_id            VARCHAR2(250) NOT NULL,
    isbn_number          NUMBER(11) NOT NULL,
    book_title           VARCHAR2(250) NOT NULL,
    author_id            VARCHAR2(250) NOT NULL,
    shelf_id             VARCHAR2(250) NOT NULL,
    librarian_id         VARCHAR2(250) NOT NULL,
    book_return_status   VARCHAR2(250),
    book_borrow_date     DATE NOT NULL,
    book_return_date     DATE NOT NULL
);

ALTER TABLE transaction
    ADD CHECK ( book_return_status IN (
        'on-hold',
        'on-loan',
        'on-loan-and-on-hold',
        'on-shelf'
    ) );

ALTER TABLE transaction ADD CONSTRAINT transaction_pk PRIMARY KEY ( transaction_id );

ALTER TABLE book
    ADD CONSTRAINT book_author_fk_author_fkv1 FOREIGN KEY ( book_isbn_number )
        REFERENCES book_details ( isbn_number )
            ON DELETE CASCADE;

ALTER TABLE book
    ADD CONSTRAINT book_author_idfk FOREIGN KEY ( author_author_id )
        REFERENCES author ( author_id )
            ON DELETE CASCADE;

ALTER TABLE book
    ADD CONSTRAINT book_book_details_fk FOREIGN KEY ( shelf_id )
        REFERENCES shelf ( shelf_id )
            ON DELETE CASCADE;

ALTER TABLE borrowed_books
    ADD CONSTRAINT borrowed_books_patrons_fk FOREIGN KEY ( patron_id )
        REFERENCES patrons ( loginid )
            ON DELETE CASCADE;

ALTER TABLE borrowed_books
    ADD CONSTRAINT borrowed_books_transaction_fk FOREIGN KEY ( transaction_id )
        REFERENCES transaction ( transaction_id )
            ON DELETE CASCADE;

ALTER TABLE librarian
    ADD CONSTRAINT librarian_user_fk FOREIGN KEY ( loginid )
        REFERENCES library_user ( loginid )
            ON DELETE CASCADE;

ALTER TABLE patrons
    ADD CONSTRAINT patrons_user_fk FOREIGN KEY ( loginid )
        REFERENCES library_user ( loginid )
            ON DELETE CASCADE;

ALTER TABLE transaction
    ADD CONSTRAINT traansaction_book_fk FOREIGN KEY ( author_id,
                                                      isbn_number,
                                                      shelf_id )
        REFERENCES book ( author_author_id,
                          book_isbn_number,
                          shelf_id )
            ON DELETE CASCADE;

ALTER TABLE transaction
    ADD CONSTRAINT traansaction_librarian_fk FOREIGN KEY ( librarian_id )
        REFERENCES librarian ( loginid )
            ON DELETE CASCADE;

ALTER TABLE transaction
    ADD CONSTRAINT traansaction_patrons_fk FOREIGN KEY ( patron_id )
        REFERENCES patrons ( loginid )
            ON DELETE CASCADE;