package org.example.lab3.repos;

import org.example.lab3.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
}
