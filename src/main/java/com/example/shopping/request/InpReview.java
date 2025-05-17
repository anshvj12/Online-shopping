package com.example.shopping.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InpReview {

    protected String reviewText;

    protected int rating;

    protected int productId;

    protected int userId;

    protected int orderId;

}
