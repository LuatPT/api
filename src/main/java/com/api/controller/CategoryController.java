package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.entity.Category;
import com.api.service.CategoryService;

@RestController
@RequestMapping("/v1")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "/categorys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> listAllCategory() {
		List<Category> listCategory = categoryService.findAllCategory();
		return new ResponseEntity<List<Category>>(listCategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/categorys/{category_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") Integer categoryId) {
		Category category = categoryService.findById(categoryId);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/categorys", method = RequestMethod.POST)
	public ResponseEntity<Category> createcategory(@RequestBody Category category, UriComponentsBuilder builder) {
		categoryService.save(category);
		HttpHeaders headers = new HttpHeaders();
		// Set url
		headers.setLocation(builder.path("/categorys/{categoryId}").buildAndExpand(category.getCategoryId()).toUri());
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categorys/{category_id}", method = RequestMethod.PUT)
	public ResponseEntity<Category> updatecategory(@PathVariable("category_id") Integer categoryId,
			@RequestBody Category category) {
		Category currentcategory = categoryService.findById(categoryId);

		if (currentcategory== null) {
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		}

		currentcategory.setCategoryId(category.getCategoryId());
		currentcategory.setCategoryName(category.getCategoryName());
		currentcategory.setCategoryStatus(category.getCategoryStatus());
		categoryService.save(currentcategory);
		return new ResponseEntity<Category>(currentcategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/categorys/{category_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deletecategory(@PathVariable("category_id") Integer categoryId) {
		Category category = categoryService.findById(categoryId);
		if (category== null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		categoryService.remove(category);
		return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
	}
}
