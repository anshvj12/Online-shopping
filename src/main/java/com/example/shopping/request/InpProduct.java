package com.example.shopping.request;

import com.example.shopping.Model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InpProduct {

    protected Integer productId;

    protected String productName;

    protected Double price;

    protected Integer availableQuantity;

    protected int addressId;

    protected Date productDate;
}
