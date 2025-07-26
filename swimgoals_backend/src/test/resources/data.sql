-- ROLES
INSERT INTO ROLES(id, name) VALUES (1, 'admin');
INSERT INTO ROLES(id, name) VALUES (2, 'coach');
INSERT INTO ROLES(id, name) VALUES (3, 'swimmer');

-- SWIMS
INSERT INTO swims(id, name) VALUES
    (1, 'Papillon'),
    (2, 'Dos'),
    (3, 'Brasse'),
    (4, 'Crawl'),
    (5, '4 Nages'),
    (6, 'Jambes'),
    (7, 'Nage Libre');

-- USERS
INSERT INTO USERS (id, lastname, firstname, email, password, role_id) VALUES
    (1, 'Doe', 'John', 'john.doe@example.com', 'password123', 1),
    (2, 'Smith', 'Alice', 'alice.smith@example.com', 'securepass456', 2),
    (3, 'Dupont', 'Martin', 'mdupont@example.com', 'highpass456', 2),
    (4, 'Brown', 'Michael', 'michael.brown@example.com', 'mypassword789', 3),
    (5, 'Johnson', 'Emily', 'emily.johnson@example.com', 'strongpass321', 3),
    (6, 'Williams', 'David', 'david.williams@example.com', 'supersecret654', 3);

-- GROUPS
INSERT INTO GROUPS(id, coach_id, name) VALUES
    (1, 2, 'Group1'),
    (2, 3, 'Group2'),
    (3, 2, 'Group3'),
    (4, 3, 'Group4'),
    (5, 3, 'Group5');

-- Affectation des groupes aux nageurs
UPDATE USERS SET group_id = 1 WHERE id = 4;
UPDATE USERS SET group_id = 2 WHERE id = 5;
UPDATE USERS SET group_id = 4 WHERE id = 6;

-- OBJECTIVES
INSERT INTO objectives (id, swimmer_id, swim_id, distance, time) VALUES
    (1, 4, 4, '100m', '00:01:45'),
    (2, 5, 2, '200m', '00:03:20'),
    (3, 6, 1, '50m',  '00:00:55'),
    (4, 4, 3, '100m', '00:01:30');

-- GOALS
INSERT INTO goals (id, objective_id, time, date) VALUES
    (1, 1, '00:01:40', '2024-01-10'),
    (2, 1, '00:01:38', '2024-02-15'),
    (3, 2, '00:03:10', '2024-01-20'),
    (4, 3, '00:00:52', '2024-03-05'),
    (5, 4, '00:01:28', '2024-04-12');
