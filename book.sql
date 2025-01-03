CREATE DATABASE IF NOT EXISTS bookdb;
USE bookdb;

CREATE TABLE IF NOT EXISTS Customer (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    SchoolName VARCHAR(255),
    Address TEXT,
    DOB DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Genres (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Publishers (
    PublisherID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Categories (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Books (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    BookName VARCHAR(255) NOT NULL,
    Description TEXT,
    Status ENUM('Available', 'Unavailable', 'Reserved') NOT NULL DEFAULT 'Available',
    GenID INT NOT NULL,
    PublisherID INT NOT NULL,
    CategoryID INT NOT NULL,
    FOREIGN KEY (GenID) REFERENCES Genres(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PublisherID) REFERENCES Publishers(PublisherID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Borrow (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    BorrowDate DATE NOT NULL,
    ReturnDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customer(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS BorrowDetail (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    BorrowID INT NOT NULL,
    BookID INT NOT NULL,
    BorrowDate DATE NOT NULL,
    ReturnDate DATE,
    ReturnStatus ENUM('Pending', 'Returned', 'Overdue') NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (BorrowID) REFERENCES Borrow(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (BookID) REFERENCES Books(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Genres (Name) VALUES
    ('Fiction'),
    ('Non-fiction'),
    ('Mystery'),
    ('Fantasy');

INSERT INTO Publishers (Name) VALUES
    ('Penguin Random House'),
    ('HarperCollins'),
    ('Macmillan Publishers');

INSERT INTO Categories (Name) VALUES
    ('Adventure'),
    ('Biography'),
    ('Romance'),
    ('Science Fiction');

INSERT INTO Books (BookName, Description, Status, GenID, PublisherID, CategoryID) VALUES
    ('The Hobbit', 'A fantasy novel by J.R.R. Tolkien.', 'Available', 4, 1, 1),
    ('1984', 'Dystopian novel by George Orwell.', 'Unavailable', 2, 2, 2),
    ('To Kill a Mockingbird', 'Novel by Harper Lee.', 'Available', 1, 3, 1);

INSERT INTO Customer (Name, SchoolName, Address, DOB) VALUES
    ('John Doe', 'Greenwood High', '123 Elm Street', '2001-01-15'),
    ('Jane Smith', 'Riverdale Academy', '456 Oak Avenue', '1999-07-25');

INSERT INTO Borrow (CustomerID, BorrowDate, ReturnDate) VALUES
    (1, '2025-01-01', NULL),
    (2, '2025-01-02', '2025-01-10');

INSERT INTO BorrowDetail (BorrowID, BookID, BorrowDate, ReturnDate, ReturnStatus) VALUES
    (1, 1, '2025-01-01', NULL, 'Pending'),
    (2, 2, '2025-01-02', '2025-01-10', 'Returned');