package com.example.shopping.request;

import com.example.shopping.Model.Images;
import com.example.shopping.Model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InpImage {

    protected Integer imageId;

    protected int productId;

    protected String imageUrl;
}
