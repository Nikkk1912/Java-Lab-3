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
public class Wheels extends Product{
    private int wheelSize;
    private String color;
    private float weight;

    public Wheels(String title, String description, int quantity, float price, int wheelSize, String color, float weight, Warehouse warehouse) {
        super(title, description, quantity, price, warehouse);
        this.wheelSize = wheelSize;
        this.color = color;
        this.weight = weight;
    }

    public Wheels(Wheels toCopy) {
        super(toCopy);
        this.wheelSize = toCopy.getWheelSize();
        this.color = toCopy.getColor();
        this.weight = toCopy.getWeight();
    }

    public String genText() {
        return "Wheels size: " + wheelSize + " | Color: " + color + " | Weight: " + weight + "\nProduct info: " + description;
    }

}
