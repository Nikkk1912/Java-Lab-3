package org.example.lab3.repos;

import org.example.lab3.model.BodyKit;
import org.example.lab3.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepo extends JpaRepository<Warehouse, Integer> {
}
