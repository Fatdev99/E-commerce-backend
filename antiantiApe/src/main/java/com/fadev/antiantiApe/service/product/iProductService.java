package com.fadev.antiantiApe.service.product;

import com.fadev.antiantiApe.dto.ProductDTO;
import com.fadev.antiantiApe.model.Product;
import com.fadev.antiantiApe.request.AddProductRequest;
import com.fadev.antiantiApe.request.ProductUpdateRequest;

import java.util.List;

public interface iProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
    List<ProductDTO> getConvertedProducts(List<Product> products);
    ProductDTO convertToDTO(Product product);
}
