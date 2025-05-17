package com.example.shopping.service;

import com.example.shopping.Model.Images;
import com.example.shopping.Model.Product;
import com.example.shopping.dao.ImageRepository;
import com.example.shopping.dao.ProductRepository;
import com.example.shopping.request.InpImage;
import com.example.shopping.response.RespImage;
import com.example.shopping.response.RespProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ImagesServiceImp implements ImagesService {

    protected ImageRepository imageRepository;

    protected ProductRepository productRepository;

    @Autowired
    public ImagesServiceImp(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Images> getImages() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Images> getImageById(int image_id) {
        Optional<Images> byId = imageRepository.findById(image_id);
        return byId;
    }

    @Override
    public Images saveImage(Images image) {
        return imageRepository.save(image);
    }

    @Override
    public RespImage addImage(InpImage image) {
        RespImage response = new RespImage();
        Images saveImages = new Images();
        if ( image.getProductId() > 0)
        {
            Optional<Product> byId = productRepository.findById(image.getProductId());
            if (byId.isPresent()) {
                saveImages.setProducts(byId.get());
            }
        }
        if(image.getImageUrl() != null && !image.getImageUrl().isEmpty())
        {
            saveImages.setImageUrl(image.getImageUrl());
        }
        //saveImages.setImageId(0);
        Images save = imageRepository.save(saveImages);

        if (save.getProducts() != null) {
            if (save.getImageId() > 0)
            {
                response.setImageId(save.getImageId());
            }
            if (save.getProducts() != null) {
                response.setImageUrl(save.getImageUrl());
            }
            if (save.getProducts() != null) {
                response.setProductId(save.getProducts().getProductId());
            }
        }

        return response;
    }


}
