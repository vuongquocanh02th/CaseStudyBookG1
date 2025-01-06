package controller.publisher;

import dao.genre.GenreDAO;
import dao.genre.IGenreDAO;
import dao.publisher.IPublisherDAO;
import dao.publisher.PublisherDAO;
import model.Genres;
import model.Publishers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/publishers")
public class PublisherController extends HttpServlet {
    private IPublisherDAO publisherDAO;

    public void init() {
        publisherDAO = new PublisherDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("publishers", publisherDAO.getAllPublishers());
        req.getRequestDispatcher("publisher/publisher.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Publishers publisher = new Publishers(id,name);
            publisherDAO.addPublisher(publisher);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Publishers publisher = new Publishers(id,name);
            publisherDAO.updatePublisher(publisher);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            publisherDAO.deletePublisher(id);
        }
        resp.sendRedirect("publishers");
    }
}
