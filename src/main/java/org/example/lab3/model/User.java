package org.example.lab3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    @ManyToOne
    private Shop shop;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Cart> myPurchases;
    @JsonIgnore
    @OneToMany(mappedBy = "commentOwner", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Comment> comments;


    public User(String name, String surname, String login, String password) {

        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }


}
