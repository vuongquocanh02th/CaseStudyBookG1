
package dao.category;

import dbconnect.DBConnection;
import model.Categories;
import model.Publishers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM Categories";
    private static final String SELECT_CATEGORY_BY_ID = "select * from Categories where categoryID = ?";
    private static final String UPDATE_CATEGORY = "UPDATE Categories SET name = ? WHERE categoryID = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM Categories WHERE categoryID = ?";
    private static final String INSERT_CATEGORY = "INSERT INTO Categories (name) VALUES (?)";

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                categories.add(new Categories(resultSet.getInt("categoryId"), resultSet.getString("name")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean addCategory(Categories category) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);){
            statement.setString(1, category.getName());
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCategory(Categories category) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getCategoryId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Categories getCategoryById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Categories(rs.getInt("categoryId"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}