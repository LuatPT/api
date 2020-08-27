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

import com.api.entity.Product;
import com.api.service.ProductService;

@RestController
@RequestMapping("/v1")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> listAllProduct() {
		List<Product> listProduct = productService.findAllProduct();
		return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{product_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("product_id") Integer productId) {
		Product product = productService.findById(productId);

		if (product == null) {
			return new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder builder) {
		productService.save(product);
		HttpHeaders headers = new HttpHeaders();
		// Set url
		headers.setLocation(builder.path("/products/{productId}").buildAndExpand(product.getProductId()).toUri());
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{product_id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("product_id") Integer productId,
			@RequestBody Product product) {
		Product currentProduct = productService.findById(productId);

		if (currentProduct== null) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}

		currentProduct.setProductId(product.getProductId());
		currentProduct.setProductName(product.getProductName());
		currentProduct.setCategoryId(product.getCategoryId());
		currentProduct.setProductImg(product.getProductImg());
		currentProduct.setProductNote(product.getProductNote());
		currentProduct.setProductPrice(product.getProductPrice());
		currentProduct.setProductSale(product.getProductSale());
		currentProduct.setProductEndSale(product.getProductEndSale());
		
		productService.save(currentProduct);
		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{product_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteCart(@PathVariable("product_id") Integer productId) {
		Product product = productService.findById(productId);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		productService.remove(product);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
}
