--admin user
EXECUTE add_user('admin', 'Administrator', null, 'Administrator', 'password', 0000, 'N/A', 'N/A', 'N/A', 1);

--sample shelves
EXECUTE insert_shelf('FIC', 25);
EXECUTE insert_shelf('SCIFI', 5);
EXECUTE insert_shelf('BIO', 10);
EXECUTE insert_shelf('COSCI', 15);

--sample suthors
EXECUTE insert_author('HMELVILLE', 'Herman', null, 'Melville');
EXECUTE insert_author('CGRAY', 'Claudia', null, 'Gray');
EXECUTE insert_author('ACONAN', 'Arthur Conan', null, 'Doyle');

COMMIT;