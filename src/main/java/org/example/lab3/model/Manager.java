package org.example.lab3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Manager extends User {
    private boolean isAdmin;
    @ManyToOne
    private Warehouse warehouse;
    @JsonIgnore
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Cart> myResponsibleCarts;

    public Manager(String login, String password, String name, String surname, boolean isAdmin) {
        super(name, surname, login, password);
        this.isAdmin = isAdmin;
    }



}
