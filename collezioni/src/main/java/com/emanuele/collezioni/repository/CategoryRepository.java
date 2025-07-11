package com.emanuele.collezioni.repository;

import com.emanuele.collezioni.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
