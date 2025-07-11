package com.emanuele.collezioni.services;

import com.emanuele.collezioni.model.Category;
import org.springframework.stereotype.Service;
import com.emanuele.collezioni.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
     private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
