package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Product;
import com.app.enums.Category;

public interface ProductDao extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category Category);

	@Query("SELECT p From Product p where LOWER(p.title) like %:query% OR LOWER(p.description) like %:query% OR LOWER(p.category) like %:query%")
	public List<Product> searchProduct(@Param("query") String query);

	@Query("SELECT p FROM Product p " + "WHERE (p.category = :category OR :category = '') "
			+ "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) "
			+ "ORDER BY " + "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, "
			+ "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC, " + "p.createdAt DESC")
	List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice, @Param("sort") String sort);

	List<Product> findTop10ByOrderByCreatedAtDesc();

}
