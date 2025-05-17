package com.example.shopping.controller;

import com.example.shopping.Model.*;
import com.example.shopping.dao.UserRepository;
import com.example.shopping.request.InpAddress;
import com.example.shopping.response.RespAddress;
import com.example.shopping.service.AddressService;
import com.example.shopping.service.AddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    private final AddressService addressService;
    private final UserRepository userRepository;

    @Autowired
    public AddressController(AddressService addressService, UserRepository userRepository) {
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        List<Address> allAddress = addressService.getAllAddress();
        return allAddress;
    }

    @PostMapping("/address")
    public ResponseEntity<RespAddress> saveAddress(@RequestBody InpAddress inpAddress) {
        RespAddress respAddress = addressService.saveAddress(inpAddress);
        if ( respAddress != null ) {
            return new ResponseEntity<>(respAddress,HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(respAddress,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable int addressId) {
        Optional<Address> address = addressService.getAddressById(addressId);
        if(address.isPresent())
            return new ResponseEntity<>(address.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/address/user/{userId}")
    public ResponseEntity<List<RespAddress>> getAddressByUserId(@PathVariable int userId) {
        List<RespAddress> addressList = addressService.getAddressByUserId(userId);
        if (addressList.isEmpty())
        {
            return new ResponseEntity<>(addressList,HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(addressList,HttpStatus.OK);
        }
    }
}
