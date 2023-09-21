package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.Category;

import java.util.List;

public interface CategoryRepository {
    Category addCategory(Category category);
    List<Category> allCategories();
    List<Category> allCategoriesWithPagination(Integer page);
    Category findCategory(String ime);
    Category updateCategory(String ime, String novoIme, String noviOpis);
    void deleteCategory(String ime);
}
