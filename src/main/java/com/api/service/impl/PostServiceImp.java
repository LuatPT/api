package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Post;
import com.api.repository.PostRepository;
import com.api.service.PostService;

@Service
public class PostServiceImp implements PostService {
	
	private PostRepository postRepository;
 
	@Autowired
	public PostServiceImp(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<Post> findAllPost() {
		return (List<Post>) postRepository.findAll();
	}

	@Override
	public Optional<Post> findById(Integer id) {
		return postRepository.findById(id);
	}

	@Override
	public void save(Post post) {
		postRepository.save(post);
	}

	@Override
	public void remove(Post post) {
		postRepository.delete(post);
	}


}
