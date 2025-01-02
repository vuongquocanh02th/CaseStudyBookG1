package dao.category;

import model.Category;
import java.util.List;

public interface ICategoryDAO {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);
}