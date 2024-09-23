package com.fadev.antiantiApe.service.product;

import com.fadev.antiantiApe.dto.ImageDTO;
import com.fadev.antiantiApe.dto.ProductDTO;
import com.fadev.antiantiApe.exception.ProductNotFoundException;
import com.fadev.antiantiApe.model.Category;
import com.fadev.antiantiApe.model.Image;
import com.fadev.antiantiApe.model.Product;
import com.fadev.antiantiApe.repository.productRepository;
import com.fadev.antiantiApe.repository.categoryRepository;
import com.fadev.antiantiApe.repository.imageRepository;
import com.fadev.antiantiApe.request.AddProductRequest;
import com.fadev.antiantiApe.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements iProductService {
    private final productRepository productRepository;
    private final categoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final imageRepository imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                        .orElseGet(() -> {
                           Category newCategory = new Category(request.getCategory().getName());
                           return categoryRepository.save(newCategory);
                        });

        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(
                productRepository::delete,
                () -> {throw new ProductNotFoundException("Product Not Found!");}
        );
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDTO> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDTO).toList();
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDTO> imageDTOs = images.stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .toList();
        productDto.setImages(imageDTOs);
        return productDto;
    }
}
