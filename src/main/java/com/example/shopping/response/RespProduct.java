package com.example.shopping.response;

import com.example.shopping.Model.Address;
import com.example.shopping.request.InpProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RespProduct {

    protected Integer productId;

    protected String productName;

    protected Double price;

    protected Integer availableQuantity;

    protected Date productDate;

    protected int addressId;

    protected List<RespImage> imagesList;

}
