package com.brunobasques.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.brunobasques.workshopmongo.domain.Post;
import com.brunobasques.workshopmongo.domain.User;
import com.brunobasques.workshopmongo.dto.AuthorDTO;
import com.brunobasques.workshopmongo.dto.CommentDTO;
import com.brunobasques.workshopmongo.repositories.PostRepository;
import com.brunobasques.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(
				null, 
				sdf.parse("12/02/2024"), 
				"Title Post 1", 
				"Body Post 1",
				new AuthorDTO(maria));
		Post post2 = new Post(
				null, 
				sdf.parse("24/02/2024"), 
				"Title Post 2", 
				"Body Post 2",
				new AuthorDTO(alex));
		Post post3 = new Post(
				null, 
				sdf.parse("12/03/2024"), 
				"Title Post 3", 
				"Body Post 3",
				new AuthorDTO(bob));

		CommentDTO comment1 = new CommentDTO("Comment 1 - Post 1", sdf.parse("12/02/2024"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Comment 2 - Post 1", sdf.parse("12/02/2024"), new AuthorDTO(bob));
		
		CommentDTO comment3 = new CommentDTO("Comment 3 - Post 2", sdf.parse("12/02/2024"), new AuthorDTO(bob));
		CommentDTO comment4 = new CommentDTO("Comment 4 - Post 2", sdf.parse("12/02/2024"), new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3, comment4));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		maria.getPosts().add(post1);
		alex.getPosts().add(post2);
		bob.getPosts().add(post3);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}
}
