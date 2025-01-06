package dao.publisher;

import dbconnect.DBConnection;
import model.Publishers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO implements IPublisherDAO {
    private static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM Publishers";
    private static final String SELECT_PUBLISHER_BY_ID = "SELECT * FROM Publishers WHERE publisherID = ?";
    private static final String UPDATE_PUBLISHER = "UPDATE Publishers SET name = ? WHERE publisherID = ?";
    private static final String DELETE_PUBLISHER = "DELETE FROM Publishers WHERE publisherID = ?";
    private static final String INSERT_PUBLISHER = "INSERT INTO Publishers (name) VALUES (?)";

    @Override
    public List<Publishers> getAllPublishers() {
        List<Publishers> publishers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PUBLISHERS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                publishers.add(new Publishers(
                        resultSet.getInt("publisherID"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public boolean addPublisher(Publishers publisher) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PUBLISHER)) {

            statement.setString(1, publisher.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePublisher(Publishers publisher) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISHER)) {

            statement.setString(1, publisher.getName());
            statement.setInt(2, publisher.getPublisherId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePublisher(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PUBLISHER)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Publishers getPublisherById(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PUBLISHER_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Publishers(
                        resultSet.getInt("publisherID"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

