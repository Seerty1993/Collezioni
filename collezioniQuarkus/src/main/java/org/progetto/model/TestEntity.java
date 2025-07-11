package org.progetto.model;

import io.ebean.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class TestEntity extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column
    public String description;
}