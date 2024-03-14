package com.app.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
	
	private int isbn;

	private String title;

	private String description;

	private String author;

	private int price;
	
	private Long userId;
	
	private MultipartFile pic;
	
	private int discountedPrice;

	private String category;

	private String photo;
	
	private LocalDate releasedDate;
	
}
