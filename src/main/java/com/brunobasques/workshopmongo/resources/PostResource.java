package com.brunobasques.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunobasques.workshopmongo.domain.Post;
import com.brunobasques.workshopmongo.resources.util.URL;
import com.brunobasques.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id)
	{
		Post post = postService.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text)
	{
		text = URL.decodeParam(text);
		List<Post> posts = postService.findByTitle(text);
		
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
			@RequestParam(value = "minDate", defaultValue = "") String minDate)
	{
		text = URL.decodeParam(text);
		Date maxDateConverted = URL.convertDate(maxDate, new Date());
		Date minDateConverted = URL.convertDate(minDate, new Date(0L));
		List<Post> posts = postService.fullSearch(text, maxDateConverted, minDateConverted);
		
		return ResponseEntity.ok().body(posts);
	}
}
