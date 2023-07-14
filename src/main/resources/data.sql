INSERT INTO POST_COMMENT VALUES (1, null, 'test 1', 'ACCEPTED', current_date, 0);
INSERT INTO POST_COMMENT VALUES (2, 1, 'test 2', 'IN_REVIEW', current_date, 4);
INSERT INTO POST_COMMENT VALUES (3, 2, 'test 3', 'REJECTED', current_date, 6);

INSERT INTO POST_COMMENT VALUES (4, 1, 'test 4', 'ACCEPTED', current_date, 1);
INSERT INTO POST_COMMENT VALUES (5, 1, 'test 5', 'ACCEPTED', current_date, 2);
INSERT INTO POST_COMMENT VALUES (6, 1, 'test 6', 'ACCEPTED', current_date, 3);
INSERT INTO POST_COMMENT VALUES (7, 1, 'test 7', 'ACCEPTED', current_date, 4);
INSERT INTO POST_COMMENT VALUES (8, 1, 'test 8', 'ACCEPTED', current_date, 5);
INSERT INTO POST_COMMENT VALUES (9, 1, 'test 9', 'ACCEPTED', current_date, 6);

insert into USERS VALUES ('user', '$2a$10$zP0.psEMm8qfgD7bOM7RjeOOV1E86jLZjP3g5aALlLkfJTnQY9W0C', 1);
insert into AUTHORITIES VALUES ('user', 'ROLE_USER');

insert into USERS VALUES ('admin', '$2a$10$zP0.psEMm8qfgD7bOM7RjeOOV1E86jLZjP3g5aALlLkfJTnQY9W0C', 1);
insert into AUTHORITIES VALUES ('admin', 'ROLE_ADMIN');