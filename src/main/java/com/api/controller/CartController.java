package com.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.api.entity.Cart;
import com.api.service.CartService;

@RestController
@RequestMapping("/v1")
public class CartController {

	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping(value = "/carts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cart>> listAllCart() {
		List<Cart> listCart = cartService.findAllCart();
		return new ResponseEntity<List<Cart>>(listCart, HttpStatus.OK);
	}

	@RequestMapping(value = "/carts/{cart_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cart> getProductById(@PathVariable("cart_id") Integer cartId) {
		Optional<Cart> cart = cartService.findById(cartId);

		if (!cart.isPresent()) {
			return new ResponseEntity<Cart>(cart.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Cart>(cart.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/carts", method = RequestMethod.POST)
	public ResponseEntity<Cart> createCart(@RequestBody Cart cart, UriComponentsBuilder builder) {
		cartService.save(cart);
		HttpHeaders headers = new HttpHeaders();
		// Set url
		headers.setLocation(builder.path("/carts/{cartId}").buildAndExpand(cart.getCartId()).toUri());
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/carts/{cart_id}", method = RequestMethod.PUT)
	public ResponseEntity<Cart> updateCart(@PathVariable("cart_id") Integer cartId,
			@RequestBody Cart cart) {
		Optional<Cart> currentcart = cartService.findById(cartId);

		if (!currentcart.isPresent()) {
			return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
		}

		currentcart.get().setCartId(cart.getCartId());
		currentcart.get().setUserId(cart.getUserId());
		currentcart.get().setProductId(cart.getProductId());
		currentcart.get().setSoluong(cart.getSoluong());
		
		cartService.save(currentcart.get());
		return new ResponseEntity<Cart>(currentcart.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/carts/{cart_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cart> deleteCart(@PathVariable("cart_id") Integer cartId) {
		Optional<Cart> cart = cartService.findById(cartId);
		if (!cart.isPresent()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
		cartService.remove(cart.get());
		return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
	}
}
