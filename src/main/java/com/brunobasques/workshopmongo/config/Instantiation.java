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

		postRepository.saveAll(Arrays.asList(post1, post2, post3));
	}
}
