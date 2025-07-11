package com.emanuele.collezioni.repository;

import com.emanuele.collezioni.model.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<CollectionItem, Integer> {
}
