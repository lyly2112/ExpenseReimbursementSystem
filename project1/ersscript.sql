DROP TABLE ers_reimbursment;
DROP TABLE ers_users;
DROP TABLE ers_user_roles;
DROP TABLE ers_reimbursment_status;
DROP TABLE ers_reimbursment_type;


CREATE TABLE ers_user_roles (
ers_user_role_id SERIAL,
user_role VARCHAR(20) NOT NULL,
PRIMARY KEY (ers_user_role_id)
);

CREATE TABLE ers_reimbursment_type (
reimb_type_id SERIAL,
reimb_type VARCHAR(10),
PRIMARY KEY (reimb_type_id)
);

CREATE TABLE ers_reimbursment_status (
reimb_status_id SERIAL,
reimb_status VARCHAR(10),
PRIMARY KEY (reimb_status_id)
);

CREATE TABLE ers_users (
ers_users_id SERIAL NOT NULL,
ers_username VARCHAR(50) NOT NULL UNIQUE,
ers_password VARCHAR(50) NOT NULL,
user_first_name VARCHAR(100) NOT NULL,
user_last_name VARCHAR(100) NOT NULL,
user_email VARCHAR(100) NOT NULL UNIQUE,
user_role_id INT NOT NULL,
PRIMARY KEY (ers_users_id),
CONSTRAINT user_roles_fk FOREIGN KEY (user_role_id)
REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursment (
reimb_id SERIAL,
reimb_amount DECIMAL(6, 2),
reimb_submitted TIMESTAMP,
reimb_resolved TIMESTAMP,
reimb_description VARCHAR(250),
reimb_receipt BYTEA,
reimb_author INT,
reimb_resolver INT,
reimb_status_id INT,
reimb_type_id INT,
PRIMARY KEY (reimb_id),
CONSTRAINT ers_users_fk_auth FOREIGN KEY (reimb_author)
REFERENCES ers_users(ers_users_id),
CONSTRAINT ers_users_fk_reslvr FOREIGN KEY (reimb_resolver)
REFERENCES ers_users(ers_users_id),
CONSTRAINT ers_reimbursment_status_fk FOREIGN KEY (reimb_status_id)
REFERENCES ers_reimbursment_status (reimb_status_id),
CONSTRAINT ers_reimbursment_type_fk FOREIGN KEY (reimb_type_id)
REFERENCES ers_reimbursment_type (reimb_type_id)
);


INSERT INTO ers_user_roles VALUES (DEFAULT, 'employee');
INSERT INTO ers_user_roles VALUES (DEFAULT, 'finance manager');

INSERT INTO  ers_reimbursment_type VALUES (DEFAULT, 'lodging');
INSERT INTO  ers_reimbursment_type VALUES (DEFAULT, 'travel');
INSERT INTO  ers_reimbursment_type VALUES (DEFAULT, 'food');
INSERT INTO  ers_reimbursment_type VALUES (DEFAULT, 'other');

INSERT INTO ers_reimbursment_status VALUES (DEFAULT, 'pending');
INSERT INTO ers_reimbursment_status VALUES (DEFAULT, 'approved');
INSERT INTO ers_reimbursment_status VALUES (DEFAULT, 'denied');

INSERT INTO ers_users VALUES 
(DEFAULT, 
'ers_username',
'ers_password',
'user_first_name',
'user_last_name',
'user_email',
1);

INSERT INTO ers_reimbursment VALUES (
DEFAULT,
100.1,
'2016-06-21 19:10:25-07',
'2016-06-22 19:10:25-07',
'reimb_description',
'reimb_receipt BYTEA',
1,
1,
1,
1
);


SELECT * FROM ers_user_roles;
SELECT * FROM ers_reimbursment_type;
SELECT * FROM ers_reimbursment_status;
SELECT * FROM ers_users;
SELECT * FROM ers_reimbursment;


