use bookmanagement;

create table Genre(
	id int auto_increment primary key,
    genreName varchar(100) not null
);

create table Publisher(
	id int auto_increment primary key,
    publisherName varchar(100) not null
);

create table Book(
	id int auto_increment primary key,
    title varchar(100) not null,
    image varchar(200) not null,
    state boolean not null,
    genre_id int not null,
    publisher_id int not null,
    foreign key(genre_id) references Genre(id),
    foreign key(publisher_id) references Publisher(id)
);

create table Student(
	id int auto_increment primary key,
    studentName varchar(100) not null,
    class varchar(10) not null,
    dob date not null
);

create table BorrowCard(
	id int auto_increment primary key,
    student_id int,
    book_id int,
    borrowDate date,
    returnDate date,
    borrowState enum('Borrowing', 'Expired', 'Returned') default 'Borrowing',
    foreign key (student_id) references Student(id),
    foreign key (book_id) references Book(id)
);

create table User(
	username varchar(50) primary key unique,
    password varchar(50) not null,
    role enum('admin', 'librarian', 'user') default 'user'
);

create table BookCategory(
	id int auto_increment primary key,
    categoryName varchar(100) not null unique
);

alter table Book 
add column category_id int not null,
add foreign key(category_id) references BookCategory(id);

