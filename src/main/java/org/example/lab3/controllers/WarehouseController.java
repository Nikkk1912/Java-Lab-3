package org.example.lab3.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.lab3.model.Manager;
import org.example.lab3.model.Product;
import org.example.lab3.model.Warehouse;
import org.example.lab3.repos.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseRepo warehouseRepo;

    @GetMapping(value = "/getAllWarehouses")
    public @ResponseBody Iterable<Warehouse> getAllWarehouses() {return warehouseRepo.findAll();}


    @PostMapping(value = "/createWarehouse")
    public @ResponseBody Warehouse createSpoiler(@RequestBody Warehouse warehouse){
        warehouseRepo.save(warehouse);
        return new Warehouse();
    }


    @PutMapping(value = "/updateWarehouseObject")
    public @ResponseBody Warehouse updateWarehouseObject(@RequestBody Warehouse warehouse) {
        warehouseRepo.save(warehouse);
        return warehouse;
    }
    @PutMapping(value = "/updateWarehouse")
    public @ResponseBody Warehouse updateWarehouse(@RequestBody String info) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(info, Properties.class);

        var id = Integer.parseInt(properties.getProperty("id"));
        var address = properties.getProperty("address");
        List<Integer> stockIds = gson.fromJson(properties.getProperty("stock"), new TypeToken<List<Integer>>() {}.getType());
        List<Integer> managerIds = gson.fromJson(properties.getProperty("managers"), new TypeToken<List<Integer>>() {}.getType());

        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            warehouse.setAddress(address);


            if (stockIds != null) {
                warehouse.getStock().clear();
                stockIds.forEach(productId -> {
                    Product product = new Product();
                    product.setId(productId);
                    warehouse.getStock().add(product);
                });
            }

            // Update managers if provided
            if (managerIds != null) {
                warehouse.getManagers().clear();
                managerIds.forEach(managerId -> {
                    Manager manager = new Manager();
                    manager.setId(managerId);
                    warehouse.getManagers().add(manager);
                });
            }

            Warehouse updatedWarehouse = warehouseRepo.save(warehouse);
            return updatedWarehouse;
        } else {
            throw new RuntimeException("Warehouse with ID " + id + " not found");
        }
    }


    @DeleteMapping(value = "/deleteWarehouse/{id}")
    public @ResponseBody Optional<Warehouse> deleteWarehouse(@PathVariable(name = "id") int id) {
        warehouseRepo.deleteById(id);
        return warehouseRepo.findById(id);
    }

}
