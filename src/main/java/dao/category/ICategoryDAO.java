package dao.category;

import model.Categories;
import model.Publishers;

import java.util.List;

public interface ICategoryDAO {
    List<Categories> getAllCategories();
    boolean addCategory(Categories category);
    boolean updateCategory(Categories category);
    boolean deleteCategory(int id);
    Categories getCategoryById(int id);
}
