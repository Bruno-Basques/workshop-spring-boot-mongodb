package com.brunobasques.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunobasques.workshopmongo.domain.Post;
import com.brunobasques.workshopmongo.domain.User;
import com.brunobasques.workshopmongo.dto.UserDTO;
import com.brunobasques.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll()
	{
		List<User> userList = userService.findAll();
		List<UserDTO> userDTOList = userList
				.stream()
				.map(x -> new UserDTO(x))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(userDTOList);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id)
	{
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO)
	{
		User user = userService.fromDTO(userDTO);
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO userDTO)
	{
		User user = userService.fromDTO(userDTO);
		user.setId(id);
		user = userService.update(user);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id)
	{
		userService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findAllPostsByUserId(@PathVariable String id)
	{
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(user.getPosts());
	}
}
