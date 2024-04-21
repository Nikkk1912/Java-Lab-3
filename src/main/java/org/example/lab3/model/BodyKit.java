package org.example.lab3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lab3.enums.KitType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class BodyKit extends Product {
    private String brand;
    private String compatibleCars;
    private String countryManufacturer;
    @Enumerated(EnumType.STRING)
    private KitType kitType;

    public BodyKit(String title, String description, int quantity, float price, String brand, String compatibleCars, String countryManufacturer, KitType kitType, Warehouse warehouse) {
        super(title, description, quantity, price, warehouse);
        this.brand = brand;
        this.compatibleCars = compatibleCars;
        this.countryManufacturer = countryManufacturer;
        this.kitType = kitType;
    }

    public BodyKit (BodyKit toCopy) {
        super(toCopy);
        this.brand = toCopy.getBrand();
        this.compatibleCars = toCopy.getCompatibleCars();
        this.countryManufacturer = toCopy.getCountryManufacturer();
        this.kitType = toCopy.getKitType();
    }

    public String genText() {
        return "Brand: " + brand + " | Country: " + countryManufacturer + " | Kit type: " + kitType + "\nCompatible cars: " + compatibleCars + "\nProduct info: " + description;
    }
}
