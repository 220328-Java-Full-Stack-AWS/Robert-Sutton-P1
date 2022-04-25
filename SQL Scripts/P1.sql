-- Robert J. Sutton  Project P1  Table Scripts 

SET SEARCH_PATH to p1, "$user", public;
SHOW SEARCH_PATH;

DROP TABLE ERS_REIMBURSEMENT;
CREATE TABLE ERS_REIMBURSEMENT(
	REIMB_ID    		serial PRIMARY KEY,
	REIMB_DESCRIPTION	VARCHAR(500),
	REIMB_SUBMITTED		TIMESTAMP,
	REIMB_AUTHOR_ID		INT,
	REIMB_TYPE_ID		INT,
	REIMB_AMOUNT		DECIMAL(10, 2),
	REIMB_VENDOR		VARCHAR(200),
	REIMB_INVOICE		VARCHAR(50),
	--REIMB_RECEIPT		BLOB,
	REIMB_STATUS_ID		INT,
	REIMB_RESOLVER_ID	INT,
	CONSTRAINT ers_reimbursement_author_fkey FOREIGN KEY (REIMB_AUTHOR_ID) REFERENCES ERS_USERS (ERS_USERS_ID),
	CONSTRAINT ers_reimbursement_resolver_fkey FOREIGN KEY (REIMB_RESOLVER_ID) REFERENCES ERS_USERS (ERS_USERS_ID),
	CONSTRAINT ers_reimbursement_status_fkey FOREIGN KEY (REIMB_STATUS_ID) REFERENCES ERS_REIMBURSEMENT_STATUS (REIMB_STATUS_ID),
	CONSTRAINT ers_reimbursement_type_fkey FOREIGN KEY (REIMB_TYPE_ID) REFERENCES ERS_REIMBURSEMENT_TYPE (REIMB_TYPE_ID)
);

SELECT * FROM ers_reimbursement


DROP TABLE ers_users;
CREATE TABLE p1.ers_users(
	ERS_USERS_ID		serial PRIMARY KEY,
	ERS_USERNAME		VARCHAR(50),
	ERS_PASSWORD		VARCHAR(50),
	USER_FIRST_NAME		VARCHAR(100),
	USER_LAST_NAME		VARCHAR(100),
	USER_EMAIL			VARCHAR(150),
	USER_ROLE_ID		INT,
	CONSTRAINT ers_users_username_email_unique UNIQUE (ERS_USERNAME, USER_EMAIL),	
	CONSTRAINT ers_users_roles_fkey FOREIGN KEY (USER_ROLE_ID) REFERENCES ERS_USER_ROLES (ERS_USER_ROLE_ID)	
);
-- Give myself Admin privilege
INSERT INTO p1.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
VALUES ('suttonro', 'password', 'Robert', 'Sutton', 'robert158@revature.net', 2);

SELECT * FROM ers_users




DROP TABLE ERS_REIMBURSEMENT_TYPE;
CREATE TABLE ERS_REIMBURSEMENT_TYPE (
	REIMB_TYPE_ID		serial PRIMARY KEY,
	REIMB_TYPE			VARCHAR(20)
);

INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel-Gas');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel-Fares');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel-Lodging');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel-Food');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel-Entertain');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel-Other');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Emp-Dev-Tuition');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Emp-Dev-License');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Emp-Dev-Other');

SELECT * FROM ers_reimbursement_type




DROP TABLE ERS_USER_ROLES;
CREATE TABLE ERS_USER_ROLES (
	ERS_USER_ROLE_ID	serial PRIMARY KEY,
	USER_ROLE			VARCHAR(10)
);
-- Insert user roles
SELECT * FROM ers_user_roles
INSERT INTO ers_user_roles (user_role) VALUES ('User');
INSERT INTO ers_user_roles (user_role) VALUES ('Admin');

SELECT * FROM ers_users


DROP TABLE ERS_REIMBURSEMENT_STATUS;
CREATE TABLE ERS_REIMBURSEMENT_STATUS (
	REIMB_STATUS_ID		serial PRIMARY KEY,
	REIMB_STATUS		VARCHAR(10)
);

SELECT * FROM ers_reimbursement_status;
INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Pending');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Cancelled');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Approved');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Denied');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Completed');

/*
 * User:
          As a user, I can submit a request for reimbursement
          As a user, I can cancel a pending request for reimbursement
          As a user, I can view my pending and completed past requests for reimbursement
          As a user, I can edit my pending requests for reimbursement
          
          * As an admin, I can approve expense reimbursements
          As an admin, I can deny expense reimbursements*/











