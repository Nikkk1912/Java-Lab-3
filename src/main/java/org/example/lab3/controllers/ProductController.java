package org.example.lab3.controllers;

import com.google.gson.Gson;
import org.example.lab3.enums.KitType;
import org.example.lab3.model.*;
import org.example.lab3.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Properties;

@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SpoilerRepo spoilerRepo;
    @Autowired
    private WheelsRepo wheelsRepo;
    @Autowired
    private BodyKitRepo bodyKitRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;
    @Autowired
    private CartRepo cartRepo;


    @GetMapping(value = "/getAllProducts")
    public @ResponseBody Iterable<Product> getAllProducts() {return productRepo.findAll();}
    @GetMapping(value = "/getAllSpoilers")
    public @ResponseBody Iterable<Spoiler> getAllSpoilers() {return spoilerRepo.findAll();}
    @GetMapping(value = "/getAllWheels")
    public @ResponseBody Iterable<Wheels> getAllWheels() {return wheelsRepo.findAll();}
    @GetMapping(value = "/getAllBodykits")
    public @ResponseBody Iterable<BodyKit> getAllBodyKits() {return bodyKitRepo.findAll();}


    @PostMapping(value = "/createSpoiler")
    public @ResponseBody Spoiler createSpoiler(@RequestBody Spoiler spoiler){
        spoilerRepo.save(spoiler);
        return new Spoiler();
    }
    @PostMapping(value = "/createWheels")
    public @ResponseBody Wheels createWheels(@RequestBody Wheels wheels) {
        wheelsRepo.save(wheels);
        return new Wheels();
    }
    @PostMapping(value = "/createBodyKit")
    public @ResponseBody BodyKit createBodyKit(@RequestBody BodyKit bodyKit) {
        bodyKitRepo.save(bodyKit);
        return new BodyKit();
    }


    @PutMapping(value = "/updateSpoilerObject")
    public @ResponseBody Spoiler updateSpoilerObject(@RequestBody Spoiler spoiler) {
        spoilerRepo.save(spoiler);
        return spoiler;
    }
    @PutMapping(value = "/updateSpoiler")
    public @ResponseBody Spoiler updateSpoiler(@RequestBody String info) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(info, Properties.class);

        var id = Integer.parseInt(properties.getProperty("id"));
        var title = properties.getProperty("title");
        var description = properties.getProperty("description");
        var quantity = Integer.parseInt(properties.getProperty("quantity"));
        var price = Float.parseFloat(properties.getProperty("price"));
        var material = properties.getProperty("material");
        var weight = Float.parseFloat(properties.getProperty("weight"));

        Optional<Spoiler> optionalSpoiler = spoilerRepo.findById(id);
        if (optionalSpoiler.isPresent()) {
            Spoiler spoiler = optionalSpoiler.get();
            spoiler.setTitle(title);
            spoiler.setDescription(description);
            spoiler.setQuantity(quantity);
            spoiler.setPrice(price);
            spoiler.setMaterial(material);
            spoiler.setWeight(weight);

            Spoiler updatedSpoiler = spoilerRepo.save(spoiler);
            return updatedSpoiler;
        } else {
            throw new RuntimeException("Spoiler with ID " + id + " not found");
        }
    }
    @PutMapping(value = "/updateBodyKitObject")
    public @ResponseBody BodyKit updateBodyKitObject(@RequestBody BodyKit bodyKit) {
        bodyKitRepo.save(bodyKit);
        return bodyKit;
    }
    @PutMapping(value = "/updateBodyKit")
    public @ResponseBody BodyKit updateBodyKit(@RequestBody String info) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(info, Properties.class);

        var id = Integer.parseInt(properties.getProperty("id"));
        var title = properties.getProperty("title");
        var description = properties.getProperty("description");
        var quantity = Integer.parseInt(properties.getProperty("quantity"));
        var price = Float.parseFloat(properties.getProperty("price"));
        var brand = properties.getProperty("brand");
        var compatibleCars = properties.getProperty("compatibleCars");
        var countryManufacturer = properties.getProperty("countryManufacturer");
        var kitType = KitType.valueOf(properties.getProperty("kitType"));


        Optional<BodyKit> optionalBodyKit = bodyKitRepo.findById(id);
        if (optionalBodyKit.isPresent()) {
            BodyKit bodyKit = optionalBodyKit.get();

            bodyKit.setTitle(title);
            bodyKit.setDescription(description);
            bodyKit.setQuantity(quantity);
            bodyKit.setPrice(price);
            bodyKit.setBrand(brand);
            bodyKit.setCompatibleCars(compatibleCars);
            bodyKit.setCountryManufacturer(countryManufacturer);
            bodyKit.setKitType(kitType);

            BodyKit updatedBodyKit = bodyKitRepo.save(bodyKit);
            return updatedBodyKit;
        } else {
            throw new RuntimeException("Body kit with ID " + id + " not found");
        }
    }
    @PutMapping(value = "/updateWheelsObject")
    public @ResponseBody Wheels updateWheelsObject(@RequestBody Wheels wheels) {
        wheelsRepo.save(wheels);
        return wheels;
    }
    @PutMapping(value = "/updateWheels")
    public @ResponseBody Wheels updateWheels(@RequestBody String info) {

        Gson gson = new Gson();
        Properties properties = gson.fromJson(info, Properties.class);

        var id = Integer.parseInt(properties.getProperty("id"));
        var title = properties.getProperty("title");
        var description = properties.getProperty("description");
        var quantity = Integer.parseInt(properties.getProperty("quantity"));
        var price = Float.parseFloat(properties.getProperty("price"));
        var wheelSize = Integer.parseInt(properties.getProperty("wheelSize"));
        var color = properties.getProperty("color");
        var weight = Float.parseFloat(properties.getProperty("weight"));

        Optional<Wheels> optionalWheels = wheelsRepo.findById(id);
        if (optionalWheels.isPresent()) {
            Wheels wheels = optionalWheels.get();

            wheels.setTitle(title);
            wheels.setDescription(description);
            wheels.setQuantity(quantity);
            wheels.setPrice(price);
            wheels.setWheelSize(wheelSize);
            wheels.setColor(color);
            wheels.setWeight(weight);

            Wheels updatedWheels = wheelsRepo.save(wheels);
            return updatedWheels;
        } else {
            throw new RuntimeException("Wheels with ID " + id + " not found");
        }
    }


    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            if (product.getCart() != null) {
                Cart cart = product.getCart();
                cart.getItemsToBuy().remove(product);
                cartRepo.save(cart);
            }

            if (product.getWarehouse() != null) {
                Warehouse warehouse = product.getWarehouse();
                warehouse.getStock().remove(product);
                warehouseRepo.save(warehouse);
            }

            productRepo.deleteById(id);

            return ResponseEntity.ok().body("Product with ID " + id + " has been deleted successfully.");
        } else {
            return new ResponseEntity<>("Product with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}
