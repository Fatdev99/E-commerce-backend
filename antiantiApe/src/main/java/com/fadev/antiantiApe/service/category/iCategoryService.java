package com.fadev.antiantiApe.service.category;

import com.fadev.antiantiApe.model.Category;

import java.util.List;

public interface iCategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
}
