package service.category;

import model.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);
}