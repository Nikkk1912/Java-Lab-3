package org.example.lab3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String title;
    protected String description;
    protected int quantity;
    protected float price;
    @JsonIgnore
    @ManyToOne()
    private Warehouse warehouse;
    @JsonIgnore
    @OneToMany(mappedBy = "whichProductCommented", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Comment> comments;
    @JsonIgnore
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Shop shop;

    public Product(String title, String description, int quantity, float price, Warehouse warehouse) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.warehouse = warehouse;
    }

    public Product(Product toCopy) {
        this.title = toCopy.getTitle();
        this.description = toCopy.getDescription();
        this.quantity = toCopy.getQuantity();
        this.price = toCopy.getPrice();
        this.warehouse = toCopy.getWarehouse();
    }

}
