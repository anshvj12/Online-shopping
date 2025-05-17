package com.example.shopping.dao;

import com.example.shopping.Model.Address;
import com.example.shopping.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByUser(User userId);
}
