package com.emanuele.collezioni.services;

import com.emanuele.collezioni.model.CollectionItem;
import org.springframework.stereotype.Service;
import com.emanuele.collezioni.repository.CollectionRepository;

import java.util.List;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<CollectionItem> findAll() {
        return collectionRepository.findAll();
    }


    public void delete(Integer id) {
        collectionRepository.deleteById(id);
    }

    public CollectionItem findById(Integer id) {
        return collectionRepository.findById(id).orElse(null);
    }
}
