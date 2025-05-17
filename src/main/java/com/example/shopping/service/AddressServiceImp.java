package com.example.shopping.service;

import com.example.shopping.Model.Address;
import com.example.shopping.Model.User;
import com.example.shopping.dao.AddressRepository;
import com.example.shopping.request.InpAddress;
import com.example.shopping.response.RespAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService {

    protected AddressRepository addressRepository;

    protected UserServiceImp userServiceImp;

    @Autowired
    public AddressServiceImp(AddressRepository addressRepository, UserServiceImp userServiceImp) {
        this.addressRepository = addressRepository;
        this.userServiceImp = userServiceImp;
    }

    @Override
    public Optional<Address> getAddressById(int address_id) {
        Optional<Address> byId = addressRepository.findById(address_id);
        return byId;
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddress()
    {
        return addressRepository.findAll();
    }

    @Override
    public RespAddress saveAddress(InpAddress inpAddress) {

        Address saveAddress = new Address();
        RespAddress respAddress = new RespAddress();

        if (inpAddress !=null) {
            saveAddress.setAddressId(0);

            if(inpAddress.getFirstLine() != null && !inpAddress.getFirstLine().equals(""))
                saveAddress.setFirstLine(inpAddress.getFirstLine());
            if(inpAddress.getSecondLine() != null && !inpAddress.getSecondLine().trim().equals(""))
                saveAddress.setSecondLine(inpAddress.getSecondLine());
            if(inpAddress.getLandmarks() != null && !inpAddress.getLandmarks().isEmpty())
                saveAddress.setLandmarks(inpAddress.getLandmarks());
            if(inpAddress.getPostalCode() != null && !inpAddress.getPostalCode().equals(""))
                saveAddress.setPostalCode(inpAddress.getPostalCode());
            if(inpAddress.getCity() != null)
                saveAddress.setCity(inpAddress.getCity());
            if(inpAddress.getState() != null && !inpAddress.getState().isEmpty())
                saveAddress.setState(inpAddress.getState());
            if(inpAddress.getCountry() != null && !inpAddress.getCountry().isEmpty())
                saveAddress.setCountry(inpAddress.getCountry());
            if(inpAddress.getAddressType()>0)
                saveAddress.setAddressType(inpAddress.getAddressType());
            if(inpAddress.getUserId() > 0)
            {
                Optional<User> userById = userServiceImp.findUserById(inpAddress.getUserId());
                if(userById.isPresent()) {
                    saveAddress.setUser(userById.get());
                }
            }

            Address save = addressRepository.save(saveAddress);

            if (save != null) {
                if( save.getAddressId()>0)
                {
                    respAddress.setAddressId(save.getAddressId());
                }
                if( save.getFirstLine() != null && !save.getFirstLine().equals(""))
                    respAddress.setFirstLine(save.getFirstLine());
                if(save.getSecondLine() != null && !save.getSecondLine().trim().equals(""))
                    respAddress.setSecondLine(save.getSecondLine());
                if(save.getLandmarks() != null && !save.getLandmarks().isEmpty())
                    respAddress.setLandmarks(save.getLandmarks());
                if(save.getPostalCode() != null && !save.getPostalCode().equals(""))
                    respAddress.setPostalCode(save.getPostalCode());
                if(save.getCity() != null && !save.getCity().equals(""))
                    respAddress.setCity(save.getCity());
                if(save.getState() != null && !save.getState().isEmpty())
                    respAddress.setState(save.getState());
                if(save.getCountry() != null && !save.getCountry().equals(""))
                    respAddress.setCountry(save.getCountry());
                if(save.getAddressType()>0)
                    respAddress.setAddressType(save.getAddressType());
            }
        }
        return respAddress;
    }

    @Override
    public List<RespAddress> getAddressByUserId(int userId) {

        List<RespAddress> respAddresses = new ArrayList<>();

        if (userId > 0)
        {
            Optional<User> userById = userServiceImp.findUserById(userId);
            List<Address> addressesByUserId = addressRepository.findByUser(userById.get());

            if (!addressesByUserId.isEmpty())
            {

                for (Address address : addressesByUserId) {
                    RespAddress respAddress = new RespAddress();
                    if (address.getAddressId()>0)
                        respAddress.setAddressId(address.getAddressId());
                    if (address.getFirstLine() != null && !address.getFirstLine().equals(""))
                        respAddress.setFirstLine(address.getFirstLine());
                    if (address.getSecondLine() != null && !address.getSecondLine().trim().equals(""))
                        respAddress.setSecondLine(address.getSecondLine());
                    if (address.getLandmarks() != null && !address.getLandmarks().isEmpty())
                        respAddress.setLandmarks(address.getLandmarks());
                    if (address.getPostalCode() != null && !address.getPostalCode().equals(""))
                        respAddress.setPostalCode(address.getPostalCode());
                    if (address.getCity() != null && !address.getCity().equals(""))
                        respAddress.setCity(address.getCity());
                    if (address.getState() != null && !address.getState().isEmpty())
                        respAddress.setState(address.getState());
                    if (address.getCountry() != null && !address.getCountry().equals(""))
                        respAddress.setCountry(address.getCountry());
                    if (address.getAddressType()>0)
                        respAddress.setAddressType(address.getAddressType());
                    respAddresses.add(respAddress);
                }
            }
        }

        return respAddresses;
    }
}
