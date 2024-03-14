package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.entity.Product;
import com.app.exception.ProductException;
import com.app.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class SellerProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) {
		System.out.println(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
	}

	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException{
		
		System.out.println("delete product controller .... ");
		String msg=productService.deleteProduct(productId);
		System.out.println("delete product controller .... msg "+msg);
		ApiResponse res=new ApiResponse(msg);
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct(){
		
		List<Product> products = productService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<List<Product>> recentlyAddedProduct(){
		
		List<Product> products = productService.recentlyAddedProduct();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product req,@PathVariable Long productId) throws ProductException{
		
		Product updatedProduct=productService.updateProduct(productId, req);
		
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody ProductDTO[] reqs) throws ProductException{
		
		for(ProductDTO product:reqs) {
			productService.createProduct(product);
		}
		
		ApiResponse res=new ApiResponse("products created successfully");
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}

	}
