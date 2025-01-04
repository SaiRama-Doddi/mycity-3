package com.example.hotels.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotels.Model.Product;
import com.example.hotels.Model.Shopping_Images;
import com.example.hotels.Repository.ProductRepository;
import com.example.hotels.Repository.Shopping_ImagesRepository;
import com.example.hotels.Service.Shopping_ImageService;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shopping-images")
public class Shopping_ImagesController {

    @Autowired
    private Shopping_ImagesRepository shoppingImagesRepository;

    @Autowired
    private Shopping_ImageService shopping_ImageService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list")
    public String getAllImages(Model model) {
        List<Shopping_Images> imagesList = shoppingImagesRepository.findAll();
        model.addAttribute("imagesList", imagesList);
        return "shopping_images/shopping_images"; // HTML page to display images
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Product> products = productRepository.findAll(); // Get all products
        model.addAttribute("products", products);
        model.addAttribute("shoppingImage", new Shopping_Images());
        return "shopping_images/create"; // HTML page for creating a new image
    }

    @PostMapping("/save")
    public String saveShoppingImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("productId") Long productId) throws IOException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Shopping_Images shoppingImage = new Shopping_Images();
            shoppingImage.setImages(file.getBytes()); // Save image bytes
            shoppingImage.setProduct(product.get());
            shoppingImagesRepository.save(shoppingImage);
        }
        return "redirect:/shopping-images/list"; // Redirect to images list
    }

    @GetMapping("/image/{imageId}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {
        Shopping_Images image = shopping_ImageService.getImageById(imageId);

        byte[] imageData = image.getImages(); // Assume this is a byte array from DB

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Set content type based on the image format
                .body(imageData);
    }

    // edit image
    @GetMapping("/editImage/{id}")
    public String editImage(@PathVariable Long id, Model model) {
        Shopping_Images image = shoppingImagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        model.addAttribute("image", image);
        model.addAttribute("products", productRepository.findAll());
        return "shopping_images/edit"; // Edit page template
    }

    @PostMapping("/editImage")
    public String updateImage(
            @RequestParam("imageFile") MultipartFile file,
            @ModelAttribute("image") Shopping_Images image,
            @RequestParam("product.productId") Long productId) throws IOException {

        // Fetch the existing image
        Shopping_Images existingImage = shoppingImagesRepository.findById(image.getImageId())
                .orElseThrow(() -> new RuntimeException("Image not found"));

        // Update image file if provided
        if (!file.isEmpty()) {
            image.setImages(file.getBytes());
        } else {
            image.setImages(existingImage.getImages());
        }

        // Retrieve the selected product and set it
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        image.setProduct(product);

        // Save the updated image
        shoppingImagesRepository.save(image);

        return "redirect:/shopping-images/list";
    }

    @GetMapping("/deleteImage")
    public String deleteImage(@RequestParam Long imageId) {
        shopping_ImageService.deleteImageById(imageId);
        return "redirect:/shopping-images/list";
    }

    // @GetMapping("/{imageId}")
    // public ResponseEntity<byte[]> getThubnailImage(@PathVariable Long imageId) {
    //     Shopping_Images image = shopping_ImageService.getImageById(imageId);

    //     if (image == null || image.getImages() == null) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     return ResponseEntity
    //             .ok()
    //             .contentType(MediaType.IMAGE_JPEG) // Adjust to your image type
    //             .body(image.getImages());
    // }

    @GetMapping("/product/{productId}")
    public List<String> getImagesByProductId(@PathVariable Long productId) {
        List<Shopping_Images> images = shopping_ImageService.getImagesByProductId(productId);

        // Convert each image's byte array to a downloadable URL
        return images.stream()
                     .map(image -> "/images/" + image.getImageId()) // Replace with your actual image endpoint
                     .toList();
    }

}
