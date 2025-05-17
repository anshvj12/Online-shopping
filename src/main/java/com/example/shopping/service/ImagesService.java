package com.example.shopping.service;

import com.example.shopping.Model.Images;
import com.example.shopping.request.InpImage;
import com.example.shopping.response.RespImage;

import java.util.List;
import java.util.Optional;

public interface ImagesService {

    public List<Images> getImages();

    public Optional<Images> getImageById(int image_id);

    public Images saveImage(Images image);

    public RespImage addImage(InpImage image);
}
