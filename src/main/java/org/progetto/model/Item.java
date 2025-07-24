package org.progetto.model;

import io.ebean.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item extends Model {
    @Id
    @GeneratedValue
    public Long id;

    public String name;
    public double confidence;
}
