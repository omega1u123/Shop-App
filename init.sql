CREATE DATABASE shopappdb;

CREATE TABLE Role_table (
                            id BIGINT PRIMARY KEY,
                            name VARCHAR(20) NOT NULL
);

CREATE TABLE User_table (
                            id BIGINT PRIMARY KEY,
                            login VARCHAR(20) NOT NULL,
                            password VARCHAR(20) NOT NULL,
                            role_id BIGINT NOT NULL,
                            FOREIGN KEY (role_id) REFERENCES Role_table(id)
);


CREATE TABLE Category (
                          id BIGINT PRIMARY KEY,
                          name VARCHAR(55) NOT NULL
);

CREATE TABLE Item (
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(150) NOT NULL,
                      image_url VARCHAR(255),
                      description VARCHAR(10000) NOT NULL,
                      price FLOAT NOT NULL,
                      category_id BIGINT NOT NULL,
                      FOREIGN KEY (category_id) REFERENCES Category(id)
);

CREATE TABLE Cart (
                      id BIGINT PRIMARY KEY,
                      user_id BIGINT NOT NULL,
                      item_id BIGINT NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES User_table(id),
                      FOREIGN KEY (item_id) REFERENCES Item(id)
);

BEGIN;

INSERT INTO Category (id, name) VALUES (1, 'смартфон');
INSERT INTO Category (id, name) VALUES (2, 'бытовая техника');
INSERT INTO Category (id, name) VALUES (3, 'ноутбук');
INSERT INTO Category (id, name) VALUES (4, 'телевизор');


INSERT INTO Item (id, name, price, description, category_id) VALUES (1, 'XIAOMI', 100, 'some descr...', 1);
INSERT INTO Item (id, name, price, description, category_id) VALUES (2, 'BOSCH', 255, 'some descr...', 2);
INSERT INTO Item (id, name, price, description, category_id) VALUES (3, 'ACER', 199, 'some descr...', 3);
INSERT INTO Item (id, name, price, description, category_id) VALUES (4, 'LG', 300, 'some descr...', 4);
INSERT INTO Item (id, name, price, description, category_id) VALUES (5, 'ASUS', 159, 'some descr...', 3);

INSERT INTO Role_table(id, name) VALUES (1, 'USER');
INSERT INTO Role_table(id, name) VALUES (2, 'ADMIN');

INSERT INTO user_table (id, login, password, role_id) VALUES (1, 'ilya', 123, 1);
INSERT INTO user_table (id, login, password, role_id) VALUES (2, 'admin', 123, 2);

COMMIT;




