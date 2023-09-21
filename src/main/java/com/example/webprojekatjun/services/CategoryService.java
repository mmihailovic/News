package com.example.webprojekatjun.services;

import com.example.webprojekatjun.entities.Category;
import com.example.webprojekatjun.repositories.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        Category duplicate = categoryRepository.findCategory(category.getIme());
        if(duplicate != null)
            return null;
        return categoryRepository.addCategory(category);
    }

    public List<Category> allCategories() {
        return categoryRepository.allCategories();
    }

    public List<Category> allCategoriesWithPagination(Integer page) {
        return this.categoryRepository.allCategoriesWithPagination(page);
    }
    public Category findCategory(String ime) {
        return categoryRepository.findCategory(ime);
    }

    public Category updateCategory(String ime, String novoIme,String opis) {
        return categoryRepository.updateCategory(ime,novoIme,opis);
    }

    public void deleteCategory(String ime) {
        categoryRepository.deleteCategory(ime);
    }
}
