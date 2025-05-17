package com.example.shopping.service;

import com.example.shopping.Model.Address;
import com.example.shopping.Model.Images;
import com.example.shopping.Model.Product;
import com.example.shopping.dao.AddressRepository;
import com.example.shopping.dao.ProductRepository;
import com.example.shopping.request.InpProduct;
import com.example.shopping.response.RespImage;
import com.example.shopping.response.RespProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImp implements ProductService {

    private final AddressRepository addressRepository;
    protected ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, AddressRepository addressRepository) {
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(int id)
    {
        return productRepository.findById(id);
    }

    @Override
    public Optional<RespProduct> getProductRespById(int id) {
        RespProduct respProduct = new RespProduct();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product getProduct = optionalProduct.get();
            if (getProduct.getProductId() != null) {
                respProduct.setProductId(getProduct.getProductId());
            }
            if(getProduct.getProductName() != null && !getProduct.getProductName().isEmpty())
                respProduct.setProductName(getProduct.getProductName());
            if (getProduct.getProductDate() != null)
                respProduct.setProductDate(getProduct.getProductDate());
            if (getProduct.getPrice() != null)
                respProduct.setPrice(getProduct.getPrice());
            if (getProduct.getAvailableQuantity() != null)
                respProduct.setAvailableQuantity(getProduct.getAvailableQuantity());
            Set<Images> productImage = getProduct.getProductImage();
            if (productImage != null)
            {
                List<RespImage> respImageList = new ArrayList<>();
                for( Images image: productImage)
                {
                    RespImage respImage = new RespImage();
                    respImage.setProductId(getProduct.getProductId());
                    if (image.getImageId() != null)
                        respImage.setImageId(image.getImageId());
                    if(image.getImageUrl() != null && !image.getImageUrl().isEmpty())
                    {
                        respImage.setImageUrl(image.getImageUrl());
                    }
                    respImageList.add(respImage);
                }
                respProduct.setImagesList(respImageList);
            }
        }
        Optional<RespProduct> optionalRespProduct = Optional.of(respProduct);
        return optionalRespProduct;
    }

    @Override
    public RespProduct addProduct(InpProduct product) {

        Product saveProduct = new Product();
        RespProduct respProduct = new RespProduct();

        if (product != null) {
            if (product.getProductId() != null) {
                saveProduct.setProductId(product.getProductId());
            }

            if (product.getProductName() != null && !product.getProductName().isEmpty()) {
                saveProduct.setProductName(product.getProductName());
            }
            if (product.getProductDate() != null) {
                saveProduct.setProductDate(product.getProductDate());
            }
            if ( product.getPrice() != null ) {
                saveProduct.setPrice(product.getPrice());
            }
            if ( product.getAvailableQuantity() != null ) {
                saveProduct.setAvailableQuantity(product.getAvailableQuantity());
            }
            if (product.getAddressId() > 0)
            {
                Optional<Address> byId = addressRepository.findById(product.getAddressId());
                if (byId.isPresent()) {
                    saveProduct.setAddress(byId.get());
                }
            }
//            saveProduct.setReviews(null);
//            saveProduct.setProductImage(null);
//            saveProduct.setCardProduct(null);

            saveProduct = productRepository.save(saveProduct);
        }

        // Setting saved data into Response

        if (saveProduct != null) {
            if (saveProduct.getProductId() != null) {
                respProduct.setProductId(saveProduct.getProductId());
            }
            if(saveProduct.getProductName() != null && !saveProduct.getProductName().isEmpty())
                respProduct.setProductName(saveProduct.getProductName());
            if (saveProduct.getProductDate() != null)
                respProduct.setProductDate(saveProduct.getProductDate());
            if (saveProduct.getPrice() != null)
                respProduct.setPrice(saveProduct.getPrice());
            if (saveProduct.getAvailableQuantity() != null)
                respProduct.setAvailableQuantity(saveProduct.getAvailableQuantity());
        }
        return respProduct;
    }
}
