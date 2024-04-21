package org.example.lab3.repos;

import org.example.lab3.model.BodyKit;
import org.example.lab3.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyKitRepo extends JpaRepository<BodyKit, Integer> {
}
