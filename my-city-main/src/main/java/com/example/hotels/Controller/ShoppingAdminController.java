package com.example.hotels.Controller;

import java.util.List;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotels.Model.Brand;
import com.example.hotels.Model.BrandDto;
import com.example.hotels.Model.Category;
import com.example.hotels.Model.CategoryDto;
import com.example.hotels.Model.Product;
import com.example.hotels.Model.ProductDto;
import com.example.hotels.Repository.BrandRepository;
import com.example.hotels.Repository.CategoryRepository;
import com.example.hotels.Repository.ProductRepository;

import jakarta.validation.Valid;



@Controller
public class ShoppingAdminController {
    
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/shopping-brands")
    public String getBrands(Model model){
        var brands=brandRepository.findAll(Sort.by(Sort.Direction.DESC, "brandId"));
        model.addAttribute("brands", brands);

        return "brands/brands";
    }

    @GetMapping("/shopping-brand-create")
    public String createBrand(Model model){
        BrandDto brandDto=new BrandDto();
        model.addAttribute("brandDto", brandDto);

        return "brands/create";
    }

	@PostMapping("/shopping-brand-create")
	public String addBrand(@Valid @ModelAttribute BrandDto brandDto, BindingResult result){
		if(result.hasErrors()){
			return "brands/create";
		}

		Brand brand=new Brand();
		brand.setName(brandDto.getName());
		brand.setDescription(brandDto.getDescription());
		brand.setCountry(brandDto.getCountry());

		brandRepository.save(brand);
		return "redirect:/shopping-brands";
	}

    @GetMapping("/edit")
    public String editBrand(Model model, @RequestParam Long brandId){
        Brand brand=brandRepository.findById(brandId).orElse(null);
        if(brand==null){
            return "redirect:/shopping-brands";
        }

        BrandDto brandDto=new BrandDto();
        brandDto.setBrandId(brand.getBrandId());
        brandDto.setName(brand.getName());
        brandDto.setDescription(brand.getDescription());
        brandDto.setCountry(brand.getCountry());

        model.addAttribute("brand", brand);
        model.addAttribute("brandDto", brandDto);

        return "brands/edit";
    }

    @PostMapping("/edit")
    public String editBrands(Model model, @RequestParam Long brandId, @Valid @ModelAttribute BrandDto brandDto, BindingResult result){
        Brand brand=brandRepository.findById(brandId).orElse(null);
        if(brand==null){
            return "redirect:/shopping-brands";
        }

        model.addAttribute("brand", brand);

        if(result.hasErrors()){
            return "brands/edit";
        }

        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());
        brand.setCountry(brandDto.getCountry());

        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            return "brands/edit";
        }
        return "redirect:/shopping-brands";
    }

    @GetMapping("/deleteBrand")
    public String deleteBrand(@RequestParam Long brandId){
        Brand brand=brandRepository.findById(brandId).orElse(null);
        if(brand!=null){
            brandRepository.delete(brand);
        }
        return "redirect:/brands";
    }

    //category-controller

    @GetMapping("/category")
    public String categoriesList(Model model){
        var categories=categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "categoryId"));
        model.addAttribute("categories", categories);
        return "category/category";
    }

    @GetMapping("/createCategory")
    public String createCategory(Model model){
        model.addAttribute("categoryDto", new CategoryDto());
        return "category/create";
    }

    @PostMapping("/createCategory")
    public String addCategory(@ModelAttribute @Valid CategoryDto categoryDto, BindingResult result){
        if(result.hasErrors()){
            return "category/create";
        }

        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("/editCategory")
    public String getEditCategory(Model model, @RequestParam Long categoryId){
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if(category==null){
            return "redirect:/category";
        }

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());

        model.addAttribute("category", category);
        model.addAttribute("categoryDto", categoryDto);

        return "category/edit";
    }

    @PostMapping("/editCategory")
    public String editCategories(Model model, @RequestParam Long categoryId, @Valid @ModelAttribute CategoryDto categoryDto, BindingResult result){
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if(category==null){
            return "redirect:/category";
        }

        model.addAttribute("category", category);

        if(result.hasErrors()){
            return "category/edit";
        }

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        try {
           categoryRepository.save(category);
        } catch (Exception e) {
            
            return "category/edit";
        }
        return "redirect:/category";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam Long categoryId){
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if(category!=null){
            categoryRepository.delete(category);
        }
        return "redirect:/category";
    }

    //products controller 
    
    @GetMapping("/products")
    public String productList(Model model){
        List<Product> products=productRepository.findAllWithBrandAndCategory(null);
        model.addAttribute("products", products);
        return "products/products";
    }

    @GetMapping("/createProduct")
    public String createProduct(Model model){
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/create";
    }

    @PostMapping("/createProduct")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result, Model model){
        if(productDto.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto", "imageFile", "The image file is required"));
        }

        if(result.hasErrors()){
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "products/create";
        }

        MultipartFile image=productDto.getImageFile();
        // String fileName=image.getOriginalFilename();

        try{
            Product product=new Product();
            product.setName(productDto.getName());
            product.setSpecifications(productDto.getSpecifications());
            product.setHighlights(productDto.getHighlights());
            product.setPrice(productDto.getPrice());
            product.setOriginalPrice(productDto.getOriginalPrice());
            product.setStock(productDto.getStock());
            product.setDisplay(productDto.getDisplay());
            product.setRating(productDto.getRating());
            product.setDiscount(productDto.getDiscount());
            product.setImageFileName(image.getBytes());

            Brand brand = brandRepository.findById(productDto.getBrandId()).orElseThrow(()->new RuntimeException("Brand not found"));
            product.setBrand(brand);

            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(()->new RuntimeException("Category not found"));
            product.setCategory(category);

            productRepository.save(product);
            return "redirect:/products";
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("error", "Error processing the image file");
            return "products/create";
        }
    }

    @GetMapping("/editProduct")
    public String showEditForm(@RequestParam Long productId, Model model) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setSpecifications(product.getSpecifications());
        productDto.setHighlights(product.getHighlights());
        productDto.setPrice(product.getPrice());
        productDto.setOriginalPrice(product.getOriginalPrice());
        productDto.setDisplay(product.getDisplay());
        productDto.setRating(product.getRating());
        productDto.setStock(product.getStock());
        productDto.setDiscount(product.getDiscount());
        productDto.setBrandId(product.getBrand().getBrandId());
        productDto.setCategoryId(product.getCategory().getCategoryId());

        model.addAttribute("productDto", productDto);
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/edit";
    }

    @PostMapping("/editProduct")
    public String editProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "products/edit";
        }

        try {
            Product product = productRepository.findById(productDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productDto.getProductId()));

            product.setName(productDto.getName());
            product.setSpecifications(productDto.getSpecifications());
            product.setHighlights(productDto.getHighlights());
            product.setPrice(productDto.getPrice());
            product.setOriginalPrice(productDto.getOriginalPrice());
            product.setDisplay(productDto.getDisplay());
            product.setRating(productDto.getRating());
            product.setStock(productDto.getStock());
            product.setDiscount(productDto.getDiscount());

            if (productDto.getImageFile() != null && !productDto.getImageFile().isEmpty()) {
                product.setImageFileName(productDto.getImageFile().getBytes());
            }

            Brand brand = brandRepository.findById(productDto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
            product.setBrand(brand);

            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);

            productRepository.save(product);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error processing the image file");
            return "products/edit";
        }
    } 

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long productId){
        Product product=productRepository.findById(productId).orElse(null);
        if(product!=null){
            productRepository.delete(product);
        }
        return "redirect:/products";
    }

  @GetMapping("/images/{productId}")
  public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long productId) {
      // Assume productRepository is injected and fetches the product
      Product product = productRepository.findById(productId)
              .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
  
      byte[] imageData = product.getImageFileName();
      if (imageData != null && imageData.length > 0) {
          ByteArrayResource resource = new ByteArrayResource(imageData);
  
          // Correctly setting the content type to IMAGE_JPEG
          return ResponseEntity.ok()
          .contentType(MediaType.valueOf("image/jpeg"))  // Using valueOf instead
          .body(resource);
      } else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
  }
}
