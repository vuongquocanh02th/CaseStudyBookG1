package controller.category;

import dao.category.CategoryDAO;
import dao.category.ICategoryDAO;
import dao.genre.GenreDAO;
import dao.genre.IGenreDAO;
import dao.publisher.IPublisherDAO;
import dao.publisher.PublisherDAO;
import model.Categories;
import model.Genres;
import model.Publishers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/categories")
public class CategoryController extends HttpServlet {
    private ICategoryDAO categoryDAO;

    public void init() {
        categoryDAO = new CategoryDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryDAO.getAllCategories());
        req.getRequestDispatcher("category/category.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Categories category = new Categories(id, name);
            categoryDAO.addCategory(category);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Categories category = new Categories(id, name);
            categoryDAO.updateCategory(category);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryDAO.deleteCategory(id);
        }
        resp.sendRedirect("categories");
    }
}
