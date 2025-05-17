package com.example.shopping.controller;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {

    @Test
    @Order(1)
    public void getUserTest() {
        System.out.println("getUserTest");
    }
}
