package dao.login;

import model.Login;
import utils.DBConnection;

import java.sql.*;

public class LoginDAOImpl implements ILoginDAO {

    @Override
    public boolean validate(Login login) {
        boolean status = false;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}