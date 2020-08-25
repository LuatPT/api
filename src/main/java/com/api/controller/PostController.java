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

import com.api.entity.Post;
import com.api.service.PostService;

@RestController
@RequestMapping("/v1")
public class PostController {

	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> listAllPost() {
		List<Post> listPost = postService.findAllPost();
		return new ResponseEntity<List<Post>>(listPost, HttpStatus.OK);
	}

	@RequestMapping(value = "/posts/{post_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> getProductById(@PathVariable("post_id") Integer postId) {
		Optional<Post> post = postService.findById(postId);

		if (!post.isPresent()) {
			return new ResponseEntity<Post>(post.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public ResponseEntity<Post> createPost(@RequestBody Post post, UriComponentsBuilder builder) {
		postService.save(post);
		HttpHeaders headers = new HttpHeaders();
		// Set url
		headers.setLocation(builder.path("/posts/{postId}").buildAndExpand(post.getPostId()).toUri());
		return new ResponseEntity<Post>(post, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/posts/{post_id}", method = RequestMethod.PUT)
	public ResponseEntity<Post> updatePost(@PathVariable("post_id") Integer postId,
			@RequestBody Post post) {
		Optional<Post> currentPost = postService.findById(postId);

		if (!currentPost.isPresent()) {
			return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
		}

		currentPost.get().setPostId(post.getPostId());
		currentPost.get().setUserId(post.getUserId());
		currentPost.get().setContent(post.getContent());
		currentPost.get().setCreateAt(post.getCreateAt());
		currentPost.get().setUpdateAt(post.getUpdateAt());
		currentPost.get().setHeader(post.getHeader());
		currentPost.get().setThumbNail(post.getThumbNail());
		currentPost.get().setPostCateId(post.getPostCateId());
		
		postService.save(currentPost.get());
		return new ResponseEntity<Post>(currentPost.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/posts/{post_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Post> deletePost(@PathVariable("post_id") Integer postId) {
		Optional<Post> post = postService.findById(postId);
		if (!post.isPresent()) {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
		postService.remove(post.get());
		return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
	}
}
