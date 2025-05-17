package com.example.shopping.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer productId;

    protected String productName;

    protected Double price;

    protected Integer availableQuantity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    protected Address address;

    protected Date productDate;

    protected String productDescription;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "products",  fetch = FetchType.EAGER)
    protected Set<Images> productImage = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH}
            ,mappedBy = "product", fetch = FetchType.EAGER)
    protected Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "product", fetch = FetchType.EAGER)
    protected Set<CardProduct> cardProducts = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "product", fetch = FetchType.EAGER)
    protected Set<OrderProduct> orderProducts = new HashSet<>();

}
