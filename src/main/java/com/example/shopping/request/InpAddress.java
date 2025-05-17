package com.example.shopping.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InpAddress {

    protected String firstLine;

    protected String secondLine;

    protected String landmarks;

    protected String postalCode;

    protected String city;

    protected String state;

    protected String country;

    protected int addressType;

    protected int addressId;

    protected int userId;
}
