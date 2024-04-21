package org.example.lab3.repos;

import org.example.lab3.model.Manager;
import org.example.lab3.model.Spoiler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpoilerRepo extends JpaRepository<Spoiler, Integer> {
}
