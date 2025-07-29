CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE groups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    coach_id INT,
    FOREIGN KEY (coach_id) REFERENCES users(id)
);

CREATE TABLE swims (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE objectives (
    id INT PRIMARY KEY AUTO_INCREMENT,
    swimmer_id INT,
    swim_id INT,
    distance VARCHAR(255) NOT NULL,
    time VARCHAR(255) NOT NULL,
    FOREIGN KEY (swimmer_id) REFERENCES users(id),
    FOREIGN KEY (swim_id) REFERENCES swims(id)
);

CREATE TABLE goals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    objective_id INT,
    time VARCHAR(255) NOT NULL,
    date VARCHAR(255) NOT NULL,
    FOREIGN KEY (objective_id) REFERENCES objectives(id)
);

INSERT INTO roles VALUES 
(1, "admin"), (2, "coach"), (3, "swimmer");

INSERT INTO swims VALUES 
(1, "Papillon"), (2, "Dos"), (3, "Brasse"), 
(4, "Crawl"), (5, "4 Nages"), (6, "Jambes"), 
(7, "Nage Libre");

ALTER TABLE users ADD COLUMN group_id INT;
ALTER TABLE users ADD CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES groups(id);
