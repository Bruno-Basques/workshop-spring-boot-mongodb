package com.brunobasques.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunobasques.workshopmongo.domain.User;
import com.brunobasques.workshopmongo.dto.UserDTO;
import com.brunobasques.workshopmongo.repositories.UserRepository;
import com.brunobasques.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll()
	{
		return userRepository.findAll();		
	}
	
	public User findById(String id)
	{
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found with Id:" + id));
	}
	
	public User insert(User user)
	{
		return userRepository.insert(user);
	}
	
	public User update(User user)
	{
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDTO)
	{
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
	public void deleteById(String id)
	{
		User user = findById(id);
		userRepository.delete(user);
	}
}
