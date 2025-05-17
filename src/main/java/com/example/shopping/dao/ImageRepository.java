package com.example.shopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shopping.Model.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {
}
