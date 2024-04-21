package org.example.lab3.repos;

import org.example.lab3.model.Wheels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WheelsRepo extends JpaRepository<Wheels, Integer> {
}
