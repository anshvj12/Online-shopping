package com.example.shopping.service;

import com.example.shopping.Model.*;
import com.example.shopping.dao.*;
import com.example.shopping.request.InpOrder;
import com.example.shopping.response.RespCardProduct;
import com.example.shopping.response.RespOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService {

    private final ProductRepository productRepository;
    private final CardProductRepository cardProductRepository;
    protected OrderRepository orderRepository;
    protected CardRepository cardRepository;
    protected UserRepository userRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, CardRepository cardRepository, UserRepository userRepository, ProductRepository productRepository, CardProductRepository cardProductRepository) {
        this.orderRepository = orderRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cardProductRepository = cardProductRepository;
    }

    @Override
    public Optional<RespOrder> createOrder(InpOrder inpOrder) {

        if(inpOrder.getUserID() > 0)
        {
            final Optional<User> user = userRepository.findById(inpOrder.getUserID());
            if (user.isPresent()) {
                Order order = new Order();
                final Card card = cardRepository.findByUser(user.get());
                final Set<CardProduct> cardProducts = card.getCardProducts();
                double totalPrice = 0;
                Set<OrderProduct> orderProducts = new HashSet<>();
                for (CardProduct cardProduct : cardProducts) {
                    OrderProduct orderProduct = new OrderProduct();
                    if(cardProduct.getQuantity() > 0 && (cardProduct.getProduct() != null ) )
                    {
                        final Optional<Product> changeProduct = productRepository.findById(cardProduct.getProduct().getProductId());
                        if (changeProduct.isPresent()) {
                            final Product product = changeProduct.get();
                            if (product.getAvailableQuantity() >= cardProduct.getQuantity()) {
                                orderProduct.setQuantity(cardProduct.getQuantity());
                                orderProduct.setProduct(product);
                                orderProduct.setOrder(order);
                                orderProducts.add(orderProduct);
                                totalPrice += product.getPrice() * cardProduct.getQuantity();

                                product.setAvailableQuantity(product.getAvailableQuantity() - cardProduct.getQuantity());
                                productRepository.save(product);
                            }
                        }
                        cardProductRepository.deleteByCardProductId(cardProduct.getCardProductId());
                    }
                }
                order.setTotalPrice(totalPrice);
                order.setOrderProducts(orderProducts);
                order.setUser(user.get());
                order.setOrderStatus(Order.Type.ORDERED);
                //order.setExpectedDeliveryDate(new Date(String.valueOf(LocalDate.now())));
                final Order save = orderRepository.save(order);

                RespOrder respOrder = new RespOrder();
                if(save != null)
                {
                    if(save.getOrderStatus() != null)
                    {
                        respOrder.setOrderStatus(save.getOrderStatus());
                    }

                    if (save.getTotalPrice() > 0)
                    {
                        respOrder.setTotalPrice(save.getTotalPrice());
                    }
                    List<RespCardProduct> respCardProducts = new ArrayList<>();
                    if(save.getOrderProducts() != null)
                    {
                        final Set<OrderProduct> orderProducts1 = save.getOrderProducts();
                        for (OrderProduct orderProduct : orderProducts1) {
                            RespCardProduct respCardProduct = new RespCardProduct();
                            if(orderProduct.getProduct() != null)
                            {
                                respCardProduct.setProductName(orderProduct.getProduct().getProductName());
                            }
                            if(orderProduct.getQuantity() > 0)
                            {
                                respCardProduct.setQuantity(orderProduct.getQuantity());
                            }
                            respCardProducts.add(respCardProduct);
                        }
                        respOrder.setOrderProducts(respCardProducts);
                    }
                    respOrder.setExpectedDeliveryDate(save.getExpectedDeliveryDate());
                    return Optional.of(respOrder);
                }
            }
            else
            {
                return Optional.empty();
            }
        }
        else
        {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public Optional<RespOrder> cancelOrder(Integer orderId) {
        final Optional<Order> Order = orderRepository.findById(orderId);

        if (Order.isPresent()) {
            final Order order = Order.get();
            if (order.getOrderStatus() != null && order.getOrderStatus() == com.example.shopping.Model.Order.Type.ORDERED) {

                order.setOrderStatus(com.example.shopping.Model.Order.Type.REJECTED);
                final Order save = orderRepository.save(order);
                RespOrder respOrder = new RespOrder();
                if (save.getOrderStatus() != null) {
                    respOrder.setOrderStatus(save.getOrderStatus());
                }
                if (save.getTotalPrice() > 0) {
                    respOrder.setTotalPrice(save.getTotalPrice());
                }
                return Optional.of(respOrder);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<RespOrder> acceptOrder(Integer orderId) {
        final Optional<Order> Order = orderRepository.findById(orderId);
        if (Order.isPresent()) {
            final Order order = Order.get();
            if (order.getOrderStatus() != null && order.getOrderStatus() == com.example.shopping.Model.Order.Type.ORDERED) {
                order.setOrderStatus(com.example.shopping.Model.Order.Type.ACCEPTED);
                final Order save = orderRepository.save(order);
                RespOrder respOrder = new RespOrder();
                if (save.getOrderStatus() != null) {
                    respOrder.setOrderStatus(save.getOrderStatus());
                }
                if (save.getTotalPrice() > 0) {
                    respOrder.setTotalPrice(save.getTotalPrice());
                }
                return Optional.of(respOrder);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<RespOrder> deliverOrder(Integer orderId) {
        final Optional<Order> Order = orderRepository.findById(orderId);

        if (Order.isPresent()) {
            final Order order = Order.get();
            if (order.getOrderStatus() != null && order.getOrderStatus() == com.example.shopping.Model.Order.Type.ACCEPTED) {
                order.setOrderStatus(com.example.shopping.Model.Order.Type.DELIVERED);
                final Order save = orderRepository.save(order);
                RespOrder respOrder = new RespOrder();
                if (save.getOrderStatus() != null)
                {
                    respOrder.setOrderStatus(save.getOrderStatus());
                }
                if(save.getTotalPrice() > 0 )
                {
                    respOrder.setTotalPrice(save.getTotalPrice());
                }
                return Optional.of(respOrder);
            }

        }
        return Optional.empty();
    }
}
