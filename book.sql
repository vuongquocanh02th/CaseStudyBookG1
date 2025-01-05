create database bookdb;
use bookdb;
-- Tạo bảng Customer
CREATE TABLE Customer (
                          ID INT AUTO_INCREMENT PRIMARY KEY,
                          Name VARCHAR(255) NOT NULL,
                          SchoolName VARCHAR(255),
                          Address TEXT,
                          DOB DATE NOT NULL
);

-- Tạo bảng Genres
CREATE TABLE Genres (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(255) NOT NULL

);

-- Tạo bảng Publishers
CREATE TABLE Publishers (
                            PublisherID INT AUTO_INCREMENT PRIMARY KEY,
                            Name VARCHAR(255) NOT NULL

);

-- Tạo bảng Categories
CREATE TABLE Categories (
                            CategoryID INT AUTO_INCREMENT PRIMARY KEY,
                            Name VARCHAR(255) NOT NULL

);

-- Tạo bảng Books
CREATE TABLE Books (
                       ID INT AUTO_INCREMENT PRIMARY KEY,
                       BookName VARCHAR(255) NOT NULL,
                       Description TEXT,
                       Status ENUM('Available', 'Unavailable', 'Reserved') NOT NULL DEFAULT 'Available',
                       GenID INT NOT NULL,
                       PublisherID INT NOT NULL,
                       CategoryID INT NOT NULL,
                       FOREIGN KEY (GenID) REFERENCES Genres(ID),
                       FOREIGN KEY (PublisherID) REFERENCES Publishers(PublisherID),
                       FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Tạo bảng Borrow
CREATE TABLE Borrow (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        CustomerID INT NOT NULL,
                        BorrowDate DATE NOT NULL,
                        ReturnDate DATE,
                        FOREIGN KEY (CustomerID) REFERENCES Customer(ID)
);

-- Tạo bảng BorrowDetail
CREATE TABLE BorrowDetail (
                              ID INT AUTO_INCREMENT PRIMARY KEY,
                              BorrowID INT NOT NULL,
                              BookID INT NOT NULL,
                              BorrowDate DATE NOT NULL,
                              ReturnDate DATE,
                              ReturnStatus ENUM('Pending', 'Returned', 'Overdue') NOT NULL DEFAULT 'Pending',
                              FOREIGN KEY (BorrowID) REFERENCES Borrow(ID),
                              FOREIGN KEY (BookID) REFERENCES Books(ID)
);