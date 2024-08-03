package com.brunobasques.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunobasques.workshopmongo.domain.Post;
import com.brunobasques.workshopmongo.repositories.PostRepository;
import com.brunobasques.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll()
	{
		return postRepository.findAll();		
	}
	
	public Post findById(String id)
	{
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post not found with Id:" + id));
	}
	
	public List<Post> findByTitle(String text)
	{
		// return postRepository.findByTitleContainingIgnoreCase(text);
		return postRepository.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date maxDate, Date minDate)
	{
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
		return postRepository.fullSearch(text, maxDate, minDate);
	}
}
