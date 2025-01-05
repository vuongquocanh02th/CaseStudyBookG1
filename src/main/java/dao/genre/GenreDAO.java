package dao.genre;

import dbconnect.DBConnection;
import model.Genres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenreDAO implements IGenreDAO{
    private static final String SELECT_ALL_GENRES = "SELECT * FROM Genres";
    private static final String SELECT_GENRE_BY_ID = "select * from genres where id = ?";
    private static final String UPDATE_GENRE = "UPDATE Genres SET name = ? WHERE id = ?";
    private static final String DELETE_GENRE = "DELETE FROM Genres WHERE ID = ?";
    private static final String INSERT_GENRE = "INSERT INTO Genres (name) VALUES (?)";

    @Override
    public List<Genres> getAllGenres() {
        List<Genres> genres = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GENRES);
            ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                genres.add(new Genres(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public boolean addGenre(Genres genre) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_GENRE);){
            statement.setString(1, genre.getName());
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGenre(Genres genre) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_GENRE)) {
            stmt.setString(1, genre.getName());
            stmt.setInt(2, genre.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteGenre(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_GENRE)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Genres getGenreById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_GENRE_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Genres(rs.getInt("ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}