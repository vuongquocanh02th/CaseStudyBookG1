package dao.genre;

import model.Genres;

import java.util.List;

public interface IGenreDAO {
    List<Genres> getAllGenres();
    boolean addGenre(Genres genre);
    boolean updateGenre(Genres genre);
    boolean deleteGenre(int id);
    Genres getGenreById(int id);
}
