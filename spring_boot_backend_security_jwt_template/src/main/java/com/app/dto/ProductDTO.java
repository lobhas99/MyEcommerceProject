package com.app.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Product;
import com.app.enums.Category;

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

	private int stock;

	@Enumerated(EnumType.STRING)
	private Category category;

	private LocalDate releasedDate;

}
