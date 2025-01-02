CREATE DATABASE IF NOT EXISTS BookManagementDB;
USE BookManagementDB;

CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE publishers (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       image VARCHAR(255),
                       `condition` ENUM('new', 'old') NOT NULL,
                       categoryId INT NOT NULL,
                       publisherId INT NOT NULL,
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (categoryId) REFERENCES categories(id) ON DELETE CASCADE,
                       FOREIGN KEY (publisherId) REFERENCES publishers(id) ON DELETE CASCADE
);

CREATE TABLE customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           code VARCHAR(50) UNIQUE NOT NULL,
                           schoolClass VARCHAR(255),
                           address VARCHAR(255),
                           birthDate DATE,
                           deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE borrows (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         customerId INT NOT NULL,
                         borrowDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         returnDate TIMESTAMP NULL,
                         status ENUM('Borrowed', 'Overdue', 'Returned') DEFAULT 'Borrowed',
                         FOREIGN KEY (customerId) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE borrow_details (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                borrowId INT NOT NULL,
                                bookId INT NOT NULL,
                                borrowDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                returnDate TIMESTAMP NULL,
                                returnStatus ENUM('Pending', 'Returned', 'Overdue') DEFAULT 'Pending',
                                FOREIGN KEY (borrowId) REFERENCES borrows(id) ON DELETE CASCADE,
                                FOREIGN KEY (bookId) REFERENCES books(id) ON DELETE CASCADE
);
