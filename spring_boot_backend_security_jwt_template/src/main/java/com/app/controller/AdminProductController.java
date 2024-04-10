package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.entity.Product;
import com.app.exception.ProductException;
import com.app.service.ImageHandlingService;
import com.app.service.ProductService;

@RestController
@Validated
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/admin/product")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ImageHandlingService imgService;

	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDTO p) {

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(p));
	}

	@PostMapping(value = "/images/{id}", consumes = "multipart/form-data") 
	public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload img " + id);
		return ResponseEntity.status(HttpStatus.CREATED).body(imgService.uploadImage(id, image));
	}

	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {

		String msg = productService.deleteProduct(productId);
		System.out.println("delete product controller .... msg " + msg);
		ApiResponse res = new ApiResponse(msg);

		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct() {

		List<Product> products = productService.getAllProducts();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/recent")
	public ResponseEntity<List<Product>> recentlyAddedProduct() {

		List<Product> products = productService.recentlyAddedProduct();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product req, @PathVariable Long productId)
			throws ProductException {

		Product updatedProduct = productService.updateProduct(productId, req);

		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}


}
