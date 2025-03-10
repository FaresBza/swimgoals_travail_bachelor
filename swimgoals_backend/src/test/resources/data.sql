INSERT INTO ROLES(id, name) VALUES (1, 'admin');
INSERT INTO ROLES(id, name) VALUES (2, 'coach');
INSERT INTO ROLES(id, name) VALUES (3, 'swimmer');

INSERT INTO USERS (lastname, firstname, email, password, role_id) VALUES ('Doe', 'John', 'john.doe@example.com', 'password123', 1);
INSERT INTO USERS (lastname, firstname, email, password, role_id) VALUES ('Smith', 'Alice', 'alice.smith@example.com', 'securepass456', 2);
INSERT INTO USERS (lastname, firstname, email, password, role_id) VALUES ('Brown', 'Michael', 'michael.brown@example.com', 'mypassword789', 3);
INSERT INTO USERS (lastname, firstname, email, password, role_id) VALUES ('Johnson', 'Emily', 'emily.johnson@example.com', 'strongpass321', 3);
INSERT INTO USERS (lastname, firstname, email, password, role_id) VALUES ('Williams', 'David', 'david.williams@example.com', 'supersecret654', 3);
