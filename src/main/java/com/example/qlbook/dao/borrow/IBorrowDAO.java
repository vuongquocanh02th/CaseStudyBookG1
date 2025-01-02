package com.example.qlbook.dao.borrow;

import com.example.qlbook.model.Borrow;

import java.sql.SQLException;
import java.util.List;

public interface IBorrowDAO {
    public void insertBorrow(Borrow borrow) throws SQLException;
    public boolean updateBorrow(Borrow borrow) throws SQLException;
    public boolean deleteBorrow(int id) throws SQLException;
    public Borrow selectBorrowById(int id) throws SQLException;
    public List<Borrow> selectAllBorrow() throws SQLException;
}
