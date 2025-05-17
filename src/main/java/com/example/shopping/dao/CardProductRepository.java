package com.example.shopping.dao;

import com.example.shopping.Model.CardProduct;
import jakarta.transaction.Transactional;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardProductRepository extends JpaRepository<CardProduct,Integer> {

    @Query(value = "select * from card_product where card_id=:cardId and product_id=:productId"
            ,nativeQuery = true)
    Optional<CardProduct> findByCardAndUser(@Param(value="cardId") int cardId, @Param(value="productId") int productId);

    @Query(value = "delete from card_product where card_product_id =:cardProductId",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByCardProductId(@Param(value = "cardProductId") int cardProductId);
}
