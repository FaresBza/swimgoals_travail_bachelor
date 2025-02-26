CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE groups (
    id INT PRIMARY KEY,
    coach_id VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (coach_id) REFERENCES Users(id)
);

CREATE TABLE swimmers_group (
    swimmer_id VARCHAR(255),
    group_id INT,
    PRIMARY KEY (swimmer_id, group_id),
    FOREIGN KEY (swimmer_id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE swims (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE objectives (
    id INT PRIMARY KEY,
    swimmer_id  VARCHAR(255),
    swim_id INT,
    distance VARCHAR(255) NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (swimmer_id) REFERENCES users(id),
    FOREIGN KEY (swim_id) REFERENCES swims(id)
);

CREATE TABLE goals (
    id INT PRIMARY KEY,
    objective_id INT,
    time TIME NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (objective_id) REFERENCES objectives(id)
);

INSERT INTO roles VALUES 
(1, "admin"), (2, "coach"), (3, "swimmer");

INSERT INTO swims VALUES 
(1, "Papillon"), (2, "Dos"), (3, "Brasse"), 
(4, "Crawl"), (5, "4 Nages"), (6, "Jambes"), 
(7, "Nage Libre");
