package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.dto.ProductDTO;
import com.app.entity.Product;
import com.app.enums.Category;
import com.app.exception.ProductException;

public interface ProductService {

	String deleteProduct(Long productId) throws ProductException;

	Product updateProduct(Long productId, Product product) throws ProductException;

	List<ProductDTO> getAllProducts();

	Product findProductById(Long id) throws ProductException;

	List<Product> findProductByCategory(Category category);

	List<Product> searchProduct(String query);

	Page<Product> getAllProduct(String category, Integer minPrice, Integer maxPrice, String sort, String stock,
			Integer pageNumber, Integer pageSize);

	List<Product> recentlyAddedProduct();

	String createProduct(ProductDTO req);

}
