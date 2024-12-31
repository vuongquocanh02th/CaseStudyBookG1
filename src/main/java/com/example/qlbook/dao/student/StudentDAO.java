package com.example.qlbook.dao.student;

import com.example.qlbook.model.Book;
import com.example.qlbook.model.Student;
import com.example.qlbook.utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    private static final String INSERT_STUDENT_SQL = "insert into student(id,name,className,address,brithDate,bookid) values(?,?,?,?,?,?)";
    private static final String SELECT_STUDENT_SQL = "select * from student where id=?";
    private static final String SELECT_ALL_SQL = "select * from student";
    private static final String DELETE_SQL = "delete from student where id=?";
    private static final String DELETE_BOOK_BORROW_SQL = "delete from student where bookid=?";
    private static final String UPDATE_STUDENT_SQL = "update student set name=?, className=?, address=?,brithDate=?,bookid=? where id=?";
    private static final String CONNECT_STUDENT_AND_BOOK_SQL = "SELECT s.id as student_id,s.name AS student_name,b.name AS book_name, b.description AS book_description"+
            "from student s"+"left join books b on s.book_id = b.id"    `



    public StudentDAO() {
    }

    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENT_SQL);
        try(Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getClassName());
            statement.setString(3, student.getAddress());
            statement.setTime(4, student.getBrithDate());
            statement.setInt(5, student.getBook().getId());
            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student selectStudent(int id) throws SQLException {
        Student student = null;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_SQL)) {
            statement.setInt(1, Integer.parseInt(id));
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String className = rs.getString("className");
                String address = rs.getString("address");
                Time brithDate = rs.getTime("brithDate");
                Integer = rs.getInt("bookid");
                Student newStudent = new Student(id, name, className, address, brithDate, bookid);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }
    public List<Student> selectAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String className = rs.getString("className");
                String address = rs.getString("address");
                Time brithDate = rs.getTime("brithDate");
                Integer bookid = rs.getInt("bookid");
                students.add(new Student(id,name,className,address,brithDate,bookid));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getClassName());
            statement.setString(3, student.getAddress());
            statement.setTime(4, student.getBrithDate());
            statement.setInt(5, student.getBook().getId());
            statement.setInt(6, student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ((SQLException) e).getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
