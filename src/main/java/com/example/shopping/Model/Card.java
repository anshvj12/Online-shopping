package com.example.shopping.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int cardId;

    protected double totalPrice;

    protected Date expectedDeliveryDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH , CascadeType.DETACH}
    , fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id" , nullable = true)
    protected User user;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE , CascadeType.REFRESH}
    , mappedBy = "userCards" , fetch = FetchType.EAGER )
    protected Set<CardProduct> cardProducts = new HashSet<>();

}
