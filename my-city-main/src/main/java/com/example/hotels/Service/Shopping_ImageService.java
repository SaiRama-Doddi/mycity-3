package com.example.hotels.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Shopping_Images;
import com.example.hotels.Repository.Shopping_ImagesRepository;

@Service
public class Shopping_ImageService {
    
    @Autowired
    private Shopping_ImagesRepository imagesRepository;

    public List<Shopping_Images> getImages(){
        return imagesRepository.findAll();
    }

    public Shopping_Images saveImage(Shopping_Images images){
        return imagesRepository.save(images);
    }

    public Shopping_Images getImageById(Long imageId){
        return imagesRepository.findById(imageId).orElse(null);
    }

    public String deleteImageById(Long imageId){
        imagesRepository.deleteById(imageId);
        return "Image deleted successfully";
    }

    public List<Shopping_Images> getImagesByProductId(Long productId){
        return imagesRepository.findByProduct_ProductId(productId);
    }

    public List<Shopping_Images> getAllImages(){
        return imagesRepository.findAll();
    }
}
