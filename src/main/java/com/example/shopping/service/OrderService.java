package com.example.shopping.service;

import com.example.shopping.Model.Order;
import com.example.shopping.request.InpOrder;
import com.example.shopping.response.RespOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {

    Optional<RespOrder> createOrder(InpOrder order);

    Optional<RespOrder> cancelOrder(Integer orderId);

    Optional<RespOrder> acceptOrder(Integer orderId);

    Optional<RespOrder> deliverOrder(Integer orderId);
}
