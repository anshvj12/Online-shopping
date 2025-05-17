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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int orderId;

    protected double totalPrice;

    protected Date expectedDeliveryDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH , CascadeType.DETACH}
            , fetch = FetchType.EAGER)
    @JoinColumn( name = "user_id" , nullable = true)
    protected User user;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE , CascadeType.REFRESH}
            , mappedBy = "order" , fetch = FetchType.EAGER )
    protected Set<OrderProduct> orderProducts = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Type orderStatus;

    public enum Type {
        ORDERED, ACCEPTED, REJECTED, DELIVERED
    }

}
