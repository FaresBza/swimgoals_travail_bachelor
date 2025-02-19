CREATE TABLE Roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Users (
    id CHAR(16) PRIMARY KEY,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES Roles(id),
    INDEX idx_role_id (role_id)
);

CREATE TABLE Groups (
    id CHAR(16) PRIMARY KEY,
    coach_id CHAR(16),
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (coach_id) REFERENCES Users(id),
    INDEX idx_coach_id (coach_id)
);

CREATE TABLE Swimmers_group (
    swimmer_id CHAR(16),
    group_id CHAR(16),
    PRIMARY KEY (swimmer_id, group_id),
    FOREIGN KEY (swimmer_id) REFERENCES Users(id),
    FOREIGN KEY (group_id) REFERENCES Groups(id),
    INDEX idx_swimmer_id (swimmer_id),
    INDEX idx_group_id (group_id)
);

CREATE TABLE Swims (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Objectives (
    id CHAR(16) PRIMARY KEY,
    swimmer_id  CHAR(16),
    swim_id INT,
    distance VARCHAR(255) NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (swimmer_id) REFERENCES Users(id),
    FOREIGN KEY (swim_id) REFERENCES Swims(id),
    INDEX idx_swimmer_id (swimmer_id),
    INDEX idx_swim_id (swim_id)
);

CREATE TABLE Goals (
    id CHAR(16) PRIMARY KEY,
    objective_id CHAR(16),
    time TIME NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (objective_id) REFERENCES Objectives(id),
    INDEX idx_objective_id (objective_id)
);

INSERT INTO Roles VALUES 
(1, "admin"), (2, "coach"), (3, "swimmer");

INSERT INTO Swims VALUES 
(1, "Papillon"), (2, "Dos"), (3, "Brasse"), 
(4, "Crawl"), (5, "4 Nages"), (6, "Jambes"), 
(7, "Nage Libre");
