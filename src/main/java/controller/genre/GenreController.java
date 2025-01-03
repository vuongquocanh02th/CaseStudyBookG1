package controller.genre;

import dao.genre.GenreDAO;
import dao.genre.IGenreDAO;
import model.Genres;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/genres")
public class GenreController extends HttpServlet {
    private IGenreDAO genreDAO;

    public void init() {
        genreDAO = new GenreDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", genreDAO.getAllGenres());
        req.getRequestDispatcher("genre/genre.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Genres genre = new Genres(id,name);
            genreDAO.addGenre(genre);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Genres genre = new Genres(id, name);
            genreDAO.updateGenre(genre);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            genreDAO.deleteGenre(id);
        }
        resp.sendRedirect("genres");
    }
}
