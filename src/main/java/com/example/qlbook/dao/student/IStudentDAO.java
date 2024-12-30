package com.example.qlbook.dao.student;

import com.example.qlbook.model.Book;
import com.example.qlbook.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    public void insertUser(Student student) throws SQLException;

    public Student selectStudent(int id);

    public List<Student> selectAllStudent();

    public boolean deleteStudent(int id) throws SQLException;

    public boolean deleteBookBorrow(Book book) throws SQLException;

    public boolean updateStudent(Student student) throws SQLException;
}
