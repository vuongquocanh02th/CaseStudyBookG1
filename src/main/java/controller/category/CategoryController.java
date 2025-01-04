package controller.category;

import dao.category.CategoryDAO;
import dao.category.ICategoryDAO;
import model.Categories;

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
        String action = req.getParameter("action");
        if (action == null || action.equals("listCategories")) {
            listCategories(req, resp);
        } else {
            switch (action) {
                case "editCategory":
                    showEditCategoryForm(req, resp);
                    break;
                case "deleteCategory":
                    showDeleteCategoryForm(req, resp);
                    break;
            }
        }
    }

    private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryDAO.getAllCategories());
        req.getRequestDispatcher("category/category.jsp").forward(req, resp);
    }

    private void showEditCategoryForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Categories category = categoryDAO.getCategoryById(id);
        req.setAttribute("category", category);
        req.getRequestDispatcher("category/editCategory.jsp").forward(req, resp);
    }

    private void showDeleteCategoryForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Categories category = categoryDAO.getCategoryById(id);
        req.setAttribute("category", category);
        req.getRequestDispatcher("category/deleteCategory.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addCategory".equals(action)) {
            addCategory(req, resp);
        } else if ("updateCategory".equals(action)) {
            updateCategory(req, resp);
        } else if ("confirmDeleteCategory".equals(action)) {
            deleteCategory(req, resp);
        }
    }

    private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        Categories category = new Categories(0, name);
        boolean isAdded = categoryDAO.addCategory(category);
        if (isAdded) {
            req.setAttribute("message", "Category added successfully.");
        } else {
            req.setAttribute("message", "Failed to add category.");
        }
        listCategories(req, resp);
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Categories category = new Categories(id, name);
        boolean isUpdated = categoryDAO.updateCategory(category);
        if (isUpdated) {
            req.setAttribute("message", "Category updated successfully.");
        } else {
            req.setAttribute("message", "Failed to update category.");
        }
        req.setAttribute("category", category);
        req.getRequestDispatcher("category/editCategory.jsp").forward(req, resp);
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isDeleted = categoryDAO.deleteCategory(id);
        if (isDeleted) {
            req.setAttribute("message", "Category deleted successfully.");
        } else {
            req.setAttribute("message", "Failed to delete category. There are books associated with this category.");
        }
        listCategories(req, resp);
    }
}