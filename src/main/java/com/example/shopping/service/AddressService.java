package com.example.shopping.service;

import com.example.shopping.Model.Address;
import com.example.shopping.request.InpAddress;
import com.example.shopping.response.RespAddress;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    Optional<Address> getAddressById(int address_id);

    Address saveAddress(Address address);

    List<Address> getAllAddress();

    RespAddress saveAddress(InpAddress inpAddress);

    List<RespAddress> getAddressByUserId(int userId);
}
