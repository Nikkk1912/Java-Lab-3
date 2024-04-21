package org.example.lab3.repos;


import org.example.lab3.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo  extends JpaRepository<Product, Integer> {
}
