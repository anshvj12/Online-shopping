package com.example.shopping.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int orderProductId;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id" , nullable = true )
    protected Order order;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.EAGER )
    @JoinColumn(name = "product_id" , nullable = true )
    protected Product product;

    protected int quantity;

}
