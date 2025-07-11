package org.progetto.services;


import io.ebean.Database;
import io.ebean.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.progetto.dto.CategoryDTO;
import org.progetto.exception.ServiceException;
import org.progetto.model.Category;

import java.util.List;


@ApplicationScoped
public class CategoryService {
    @Inject
    Database db;

    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = db.find(Category.class).findList();
        try {
            return categories.stream()
                    .map(CategoryDTO::of)
                    .toList();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage() + "Nessuna categoria presente");
        }
    }


    public CategoryDTO findCategoryById(Integer id) {
        Category entity = db.find(Category.class)
                .where()
                .eq("id", id)
                .findOneOrEmpty()
                .orElseThrow(() -> new ServiceException("Categoria non trovata"));

        return CategoryDTO.of(entity);
    }

    public CategoryDTO findCategoryByName(String name) {
        Category entity = db.find(Category.class)
                .where()
                .eq("name_category", name.toUpperCase())
                .findOneOrEmpty()
                .orElseThrow(() -> new ServiceException("Categoria non trovata"));

        return CategoryDTO.of(entity);
    }

    public void addCategory(CategoryDTO category) {
        try {
            db.insert(category.toEntity());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage() + " Categoria già presente o dati non validi");
        }
    }

    public void deleteCategoryById(Integer id) {
        try (Transaction tx = db.beginTransaction()) {
            Category cat = db.find(Category.class).where().eq("id", id).findOneOrEmpty().orElseThrow(() -> new ServiceException("Categoria non trovata"));
            cat.delete(tx);
            tx.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteCategoryByName(String name) {
        try (Transaction tx = db.beginTransaction()) {
            Category cat = db.find(Category.class).where().eq("name_category", name).findOneOrEmpty().orElseThrow(() -> new ServiceException("Categoria non trovata"));
            cat.delete(tx);
            tx.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
