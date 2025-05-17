package com.example.shopping.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespAddress {

    protected int addressId;

    protected String firstLine;

    protected String secondLine;

    protected String landmarks;

    protected String postalCode;

    protected String city;

    protected String state;

    protected String country;

    protected int addressType;

}
