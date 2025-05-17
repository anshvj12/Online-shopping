package com.example.shopping.service;

import com.example.shopping.Model.Product;
import com.example.shopping.request.InpProduct;
import com.example.shopping.response.RespProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> getProducts();

    public Optional<Product> getProductById(int id);

    public Optional<RespProduct> getProductRespById(int id);

    public RespProduct addProduct(InpProduct product);
}
