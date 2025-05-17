package com.example.shopping.Model;

import jakarta.persistence.*;
import com.example.shopping.Model.Product;
import lombok.Data;

@Entity
@Data
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer imageId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH},
                fetch = FetchType.EAGER)
    @JoinColumn( name = "product_id", nullable = true)
    protected Product products;

    protected String imageUrl;
}
