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

    @Override
    public void init() {
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("listCategories")) {
            listCategories(req, resp);
        } else {
            handleGetActions(req, resp, action);
        }
    }

    private void handleGetActions(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        switch (action) {
            case "editCategory":
                showEditCategoryForm(req, resp);
                break;
            case "deleteCategory":
                showDeleteCategoryForm(req, resp);
                break;
            default:
                listCategories(req, resp);
                break;
        }
    }

    private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryDAO.getAllCategories());
        req.getRequestDispatcher("category/category.jsp").forward(req, resp);
    }

    private void showEditCategoryForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseCategoryId(req);
        Categories category = categoryDAO.getCategoryById(id);
        req.setAttribute("category", category);
        req.getRequestDispatcher("category/editCategory.jsp").forward(req, resp);
    }

    private void showDeleteCategoryForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseCategoryId(req);
        Categories category = categoryDAO.getCategoryById(id);
        req.setAttribute("category", category);
        req.getRequestDispatcher("category/deleteCategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listCategories(req, resp);
        } else {
            handlePostActions(req, resp, action);
        }
    }

    private void handlePostActions(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        switch (action) {
            case "addCategory":
                addCategory(req, resp);
                break;
            case "updateCategory":
                updateCategory(req, resp);
                break;
            case "confirmDeleteCategory":
                deleteCategory(req, resp);
                break;
            default:
                listCategories(req, resp);
                break;
        }
    }

    private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Categories category = new Categories(0, name);
        String message = categoryDAO.addCategory(category) ? "Category added successfully." : "Failed to add category.";
        req.setAttribute("message", message);
        listCategories(req, resp);
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseCategoryId(req);
        String name = req.getParameter("name");
        Categories category = new Categories(id, name);
        String message = categoryDAO.updateCategory(category) ? "Category updated successfully." : "Failed to update category.";
        req.setAttribute("message", message);
        req.setAttribute("category", category);
        req.getRequestDispatcher("category/editCategory.jsp").forward(req, resp);
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseCategoryId(req);
        String message = categoryDAO.deleteCategory(id) ? "Category deleted successfully." : "Failed to delete category. There are books associated with this category.";
        req.setAttribute("message", message);
        listCategories(req, resp);
    }

    private int parseCategoryId(HttpServletRequest req) {
        return Integer.parseInt(req.getParameter("id"));
    }
}