package com.brunobasques.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunobasques.workshopmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll()
	{
		User user1 = new User("1", "User One", "user1email@gmail.com");
		User user2 = new User("2", "User Two", "user2email@gmail.com");
		User user3 = new User("3", "User Three", "user3email@gmail.com");
		
		List<User> userList = new ArrayList<>();
		userList.addAll(Arrays.asList(user1, user2, user3));
		
		return ResponseEntity.ok().body(userList);
	}
}
