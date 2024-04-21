package org.example.lab3.repos;

import org.example.lab3.model.Comment;
import org.example.lab3.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
