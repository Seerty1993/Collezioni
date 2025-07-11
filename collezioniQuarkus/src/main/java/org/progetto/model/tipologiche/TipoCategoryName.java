//package org.progetto.model.tipologiche;
//
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import io.ebean.Model;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.progetto.model.Category;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "tipo_category_name")
//public class TipoCategoryName extends Model {
//    @Id
//    @GeneratedValue
//    private Integer id;
//    @Column(nullable = false, name = "name_category")
//    private String categoryName;
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "category_id")
//    private Category category;
//}
