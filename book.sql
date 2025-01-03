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

insert into Customer(Name, SchoolName, Address, DOB) values
                                                         ('Nguyen Van E', 'THPT ZZZ','YY','1990-09-09'),
                                                         ('Nguyen Van F', 'THPT ZZZ','YY','1990-09-09'),
                                                         ('Nguyen Van C', 'THPT ZZZ','YY','1990-09-09'),
                                                         ('Nguyen Van D', 'THPT YZY','YY','1990-09-09'),
                                                         ('Nguyen Van B', 'THPT YYY','YY','1990-09-09'),
                                                         ('Nguyen Van A', 'THPT XXX','YY','1999-09-09'),
                                                         ('Vuong Quoc Anh', 'THPT Tung Thien','Son Tay','2002-04-30');

insert into Genres(name) values
                             ('Kinh di'),
                             ('Trinh tham'),
                             ('Tinh cam'),
                             ('Hai huoc'),
                             ('Tam ly');

insert into Publishers(name) values
                                 ('Pub 1'),
                                 ('Pub 2'),
                                 ('Pub 3'),
                                 ('Pub 4'),
                                 ('Pub 5');

insert into Categories(name) values
                                 ('Truyen ngan'),
                                 ('Truyen dai'),
                                 ('Truyen tranh'),
                                 ('Truyen nguoc'),
                                 ('Truyen tap');

insert into Books(BookName, Description, Status, GenID, PublisherID, CategoryID)
values
    ('Dao kinh di','No des','Available',1,1,1),
    ('Ghost House','No des','Available',1,1,1),
    ('Killer Virginia','No des','Available',1,1,1),
    ('Hilliker','No des','Available',1,1,1),
    ('Execute','No des','Available',1,1,1);

SELECT b.ID, b.BookName, b.Description, b.Status, g.Name AS GenreName,
       p.Name AS PublisherName, c.Name AS CategoryName
FROM Books b
         JOIN Genres g ON b.GenID = g.ID
         JOIN Publishers p ON b.PublisherID = p.PublisherID
         JOIN Categories c ON b.CategoryID = c.CategoryID;