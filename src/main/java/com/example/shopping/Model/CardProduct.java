package com.example.shopping.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CardProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int cardProductId;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
    fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id" ,nullable = true   )
    protected Card userCards;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
    fetch = FetchType.LAZY )
    @JoinColumn(name = "product_id" , nullable = true )
    protected Product product;

    protected int quantity;

}
