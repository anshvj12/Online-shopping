package com.example.shopping.controller;

import com.example.shopping.request.InpOrder;
import com.example.shopping.response.RespOrder;
import com.example.shopping.service.OrderService;
import com.example.shopping.service.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public RespOrder createOrder(@RequestBody InpOrder inpOrder) {
        final Optional<RespOrder> order = orderService.createOrder(inpOrder);
        if (order.isPresent())
            return order.get();
        return null;
    }

    @PutMapping("/orders/cancel/{orderId}")
    public RespOrder cancelOrder(@PathVariable Integer orderId) {
        final Optional<RespOrder> respOrder = orderService.cancelOrder(orderId);
        if (respOrder.isPresent())
            return respOrder.get();
        return null;
    }

    @PutMapping("/orders/accept/{orderId}")
    public RespOrder acceptOrder(@PathVariable Integer orderId) {
        final Optional<RespOrder> respOrder = orderService.acceptOrder(orderId);
        if (respOrder.isPresent())
            return respOrder.get();
        return null;
    }

    @PutMapping("/orders/deliver/{orderId}")
    public RespOrder deliverOrder(@PathVariable Integer orderId) {
        final Optional<RespOrder> respOrder = orderService.deliverOrder(orderId);
        if (respOrder.isPresent())
            return respOrder.get();
        return null;
    }
}
