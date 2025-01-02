package com.example.qlbook.dao.borrowdetail;

import com.example.qlbook.model.BorrowDetail;

import java.sql.SQLException;
import java.util.List;

public interface IBorrowDetailDAO {
    public void insertBorrowDetail(BorrowDetail borrowDetail) throws SQLException;
    public boolean updateBorrowDetail(BorrowDetail borrowDetail) throws SQLException;
    public boolean deleteBorrowDetail(int id) throws SQLException;
    public BorrowDetail selectBorrowDetailById(int id)throws SQLException;
    public List<BorrowDetail> selectAllBorrowDetail() throws SQLException;
}

