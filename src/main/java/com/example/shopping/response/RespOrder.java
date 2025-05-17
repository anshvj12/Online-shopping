package com.example.shopping.response;

import com.example.shopping.Model.Order;
import com.example.shopping.Model.OrderProduct;
import com.example.shopping.Model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RespOrder {

    protected double totalPrice;

    protected Date expectedDeliveryDate;

    protected User user;

    protected List<RespCardProduct> orderProducts;

    @Enumerated(EnumType.STRING)
    private Order.Type orderStatus;

    public enum Type {
        ORDERED, ACCEPTED, REJECTED, DELIVERED
    }
}
