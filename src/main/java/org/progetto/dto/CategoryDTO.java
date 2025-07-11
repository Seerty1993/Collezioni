package org.progetto.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.progetto.model.Category;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer id;
    @NotNull
    private String name_category;


    public static CategoryDTO of(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        if (entity.getName_category() != null) {
            dto.setName_category(entity.getName_category());
        }
        return dto;
    }

    public Category toEntity() {
        Category entity = new Category();
        entity.setId(this.id);
        entity.setName_category(this.name_category);
        toUpdate(entity);
        return entity;
    }

    public void toUpdate(Category entity) {
        if (this.name_category != null) {
            entity.setName_category(this.name_category);
        }
    }


}

