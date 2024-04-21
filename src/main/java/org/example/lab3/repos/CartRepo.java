package org.example.lab3.repos;

import org.example.lab3.model.Cart;
import org.example.lab3.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
