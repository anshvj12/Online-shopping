package com.example.shopping.dao;

import com.example.shopping.Model.Card;
import com.example.shopping.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByUser(User userId);

}
