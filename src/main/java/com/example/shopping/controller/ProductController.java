package com.example.shopping.controller;

import com.example.shopping.Model.Product;
import com.example.shopping.request.InpProduct;
import com.example.shopping.response.RespProduct;
import com.example.shopping.service.ProductService;
import com.example.shopping.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductServiceImp productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<RespProduct> addProduct(@RequestBody InpProduct product) {
        RespProduct respProduct = productService.addProduct(product);
        if (respProduct != null) {
            return new ResponseEntity<>(respProduct, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("products/{productId}")
    public ResponseEntity<RespProduct> getProductById(@PathVariable int productId) {
        Optional<RespProduct> product = productService.getProductRespById(productId);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
