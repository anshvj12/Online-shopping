package com.example.shopping.dao;

import com.example.shopping.Model.OrderProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Query(value = "delete from order_product where order_product_id =:orderProductId",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByOrderProductId(@Param(value = "orderProductId") int orderProductId);

}
