package com.emanuele.collezioni.ApiRest;

import io.swagger.v3.oas.annotations.Operation;
import com.emanuele.collezioni.model.CollectionItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.emanuele.collezioni.services.CollectionService;

import java.util.List;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService service) {
        this.collectionService = service;
    }

    @Operation(summary = "Recupera tutti gli item di collezione")
    @GetMapping
    public List<CollectionItem> all() {
        return collectionService.findAll();
    }

    @Operation(summary = "Elimina un item per ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {
        collectionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recupera un item per ID")
    @GetMapping("/{id}")
    public CollectionItem get(@PathVariable Integer id) {
        return collectionService.findById(id);
    }
}

