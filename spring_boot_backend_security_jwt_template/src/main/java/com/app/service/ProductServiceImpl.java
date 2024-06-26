package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dao.ProductDao;
import com.app.dto.ProductDTO;
import com.app.entity.Product;
import com.app.enums.Category;
import com.app.exception.ProductException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;


	@Autowired
	private ModelMapper mapper;

	
	@Override
	public Product addProduct(ProductDTO p) {
		Product product = mapper.map(p, Product.class);
		return productDao.save(product);
	}


	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		productDao.delete(product);
		return "Product Deleted";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product = findProductById(productId);
		if (req.getStock() != 0) {
			product.setStock(req.getStock());
		}
		if (req.getDescription() != null) {
			product.setDescription(req.getDescription());
		}
		return productDao.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();

	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> opt = productDao.findById(id);
		if (opt.isPresent())
			return opt.get();
		throw new ProductException("Product not found with id " + id);
	}

	@Override
	public List<Product> findProductByCategory(Category category) {
		List<Product> products = productDao.findByCategory(category);
		return products;
	}

	@Override
	public List<Product> searchProduct(String query) {
		List<Product> products = productDao.searchProduct(query);
		return products;
	}

	@Override
	public Page<Product> getAllProduct(String category, Integer minPrice, Integer maxPrice, String sort, String stock,
			Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productDao.filterProducts(category, minPrice, maxPrice, sort);
		if (stock != null) {
			if (stock.equals("in_stock"))
				products = products.stream().filter(p -> p.getStock() > 0).collect(Collectors.toList());
			else if (stock.equals("out_of_stock"))
				products = products.stream().filter(p -> p.getStock() < 1).collect(Collectors.toList());
		}
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

		List<Product> pageContent = products.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
		return filteredProducts;
	}

	@Override
	public List<Product> recentlyAddedProduct() {
		List<Product> products = productDao.findTop10ByOrderByCreatedAtDesc();
		return products;
	}

}


