package com.example.shopping.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int addressId;

    protected String firstLine;

    protected String secondLine;

    protected String landmarks;

    protected String postalCode;

    protected String city;

    protected String state;

    protected String country;

    protected int addressType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id" , nullable = true)
    protected User user;

}
