package org.progetto.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.progetto.dto.CategoryDTO;
import org.progetto.services.CategoryService;

import java.util.List;

@Tag(name = "Categorie")
@Path("category")

public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @GET
    @Path("/find-all")
    @Operation(summary = "findAllCategories",
            description = "Trova tutte le categorie")
    public List<CategoryDTO> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GET
    @Path("/find-id/{id}")
    @Operation(summary = "findCategoryById",
            description = "Trova una categoria specifica per id")
    public CategoryDTO findCategoryById(@PathParam("id") Integer id) {
        return categoryService.findCategoryById(id);
    }

    @GET
    @Path("/find-name/{name}")
    @Operation(summary = "findCategoryByName",
            description = "Trova una categoria specifica per nome")
    public CategoryDTO findCategoryByName(@PathParam("name") String name) {
        return categoryService.findCategoryByName(name);
    }

    @POST
    @Path("/add")
    @Operation(summary = "addCategory",
            description = "Aggiungi una nuova categoria")
    public void addCategory(@Valid CategoryDTO category) {
        categoryService.addCategory(category);
    }

    @DELETE
    @Path("/delete-id/{id}")
    @Operation(summary = "deleteCategoryById",
            description = "Elimina una categoria per id")
    public void deleteCategoryById(@PathParam("id") Integer id) {
        categoryService.deleteCategoryById(id);
    }

    @DELETE
    @Path("/delete-name/{name}")
    @Operation(summary = "deleteCategoryById",
            description = "Elimina una categoria per id")
    public void deleteCategoryByName(@PathParam("name") String name) {
        categoryService.deleteCategoryByName(name);
    }
}
