package dao.category;

import dbconnect.DBConnection;
import model.Categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM Categories";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Categories WHERE categoryID = ?";
    private static final String UPDATE_CATEGORY = "UPDATE Categories SET name = ? WHERE categoryID = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM Categories WHERE categoryID = ?";
    private static final String INSERT_CATEGORY = "INSERT INTO Categories (name) VALUES (?)";

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                categories.add(mapToCategory(resultSet));
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return categories;
    }

    @Override
    public boolean addCategory(Categories category) {
        return executeUpdate(INSERT_CATEGORY, stmt -> stmt.setString(1, category.getName())) > 0;
    }

    @Override
    public boolean updateCategory(Categories category) {
        return executeUpdate(UPDATE_CATEGORY, stmt -> {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getCategoryId());
        }) > 0;
    }

    @Override
    public boolean deleteCategory(int id) {
        return executeUpdate(DELETE_CATEGORY, stmt -> stmt.setInt(1, id)) > 0;
    }

    @Override
    public Categories getCategoryById(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapToCategory(resultSet);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    private Categories mapToCategory(ResultSet resultSet) throws SQLException {
        return new Categories(resultSet.getInt("categoryId"), resultSet.getString("name"));
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Replace with proper logging in production.
    }

    private int executeUpdate(String query, SQLConsumer<PreparedStatement> consumer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            consumer.accept(statement);
            return statement.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
            return 0;
        }
    }

    @FunctionalInterface
    private interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}
