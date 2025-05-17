package com.example.shopping.controller;

import com.example.shopping.Model.Images;
import com.example.shopping.request.InpImage;
import com.example.shopping.response.RespImage;
import com.example.shopping.service.ImagesService;
import com.example.shopping.service.ImagesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    private final ImagesService imagesService;

    @Autowired
    public ImageController(ImagesServiceImp imagesService) {
        this.imagesService = imagesService;
    }

    @GetMapping("/images")
    public List<Images> getImages() {
        return imagesService.getImages();
    }

    @PostMapping("/images")
    public ResponseEntity<RespImage> addImage(@RequestBody InpImage image)
    {
        RespImage images = imagesService.addImage(image);
        if (images != null) {
            return new ResponseEntity<>(images, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
