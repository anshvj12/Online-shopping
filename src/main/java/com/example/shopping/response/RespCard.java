package com.example.shopping.response;

import com.example.shopping.Model.CardProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RespCard {

    protected double totalPrice;

    protected Date expectedDeliveryDate;

    protected List<RespCardProduct> products;
}
