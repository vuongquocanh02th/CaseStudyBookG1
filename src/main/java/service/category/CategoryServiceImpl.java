package service.category;

import dao.category.ICategoryDAO;
import dao.category.CategoryDAOImpl;
import model.Category;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryDAO.deleteCategory(id);
    }
}