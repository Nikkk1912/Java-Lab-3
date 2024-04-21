package org.example.lab3.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Spoiler extends Product{
    private String material;
    private float weight;

    public Spoiler(String title, String description, int quantity, float price, String material, float weight, Warehouse warehouse) {
        super(title, description, quantity, price, warehouse);
        this.material = material;
        this.weight = weight;
    }

    public Spoiler(Spoiler toCopy) {
        super(toCopy);
        this.material = toCopy.getMaterial();
        this.weight = toCopy.getWeight();
    }

    public String genText() {
        return "Material: " + material + " | Weight: " + weight + "\nProduct info: " + description;
    }
}
