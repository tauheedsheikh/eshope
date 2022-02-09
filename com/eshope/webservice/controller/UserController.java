package com.eshope.webservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshope.webservice.dao.UserDao;
import com.eshope.webservice.exception.UserNotFound;
import com.eshope.webservice.modul.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@GetMapping("/users")
	public User getuserByName(@RequestParam("name") String name) {
		User user = userDao.getUserByName(name);
		if (user != null) {
			return user;
		}
		throw new UserNotFound("User Not Found With The Given Name" + name);
	}

	@GetMapping("/userss/{id}")
	public User userById(@PathVariable("id") int id) {
		User userr = userDao.getUserById(id);
		if (userr != null) {
			return userr;
		}
		throw new UserNotFound("User Not Found With The Given Id" + id);
	}

	@GetMapping("/userl")
	public List<User> getAllUser() {
		List<User> list = userDao.getAllUser();
		if (list.isEmpty()) {
			throw new UserNotFound("User Not Found With The Given Information");
		}
		return list;
	}

	@PostMapping("/register")
	public Map<String, String> addUser(@RequestBody User user) {
		int rowsAffected = userDao.insertUser(user);
		Map<String, String> response = new HashMap<String, String>();
		response.put("messaage", "user is registered sccessfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}
	
	@PostMapping("/login")
	public Map<String, String> loginUser(@RequestBody User user) {
	    User fetcheduser =  userDao.loginUser(user.getEmail());
	    if(fetcheduser != null) {
	    	if(fetcheduser.getPassword().equals(user.getPassword())) {
	    		Map<String, String> response = new HashMap<String, String>();
	    		response.put("messaage", "user is login sccessfully");
	    		response.put("status", "successfull");
	    		return response;
	    	} else {
	    		throw new UserNotFound("Invalid Password , Password not match");
	    	}
	    }
	    throw new UserNotFound("User Not Found with email = " +user.getEmail());
	}

	@PutMapping("useru")
	public Map<String, String> updateUser(@RequestBody User user) {
		int rowsAffected = userDao.updateUser(user);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "User Update Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@DeleteMapping("/userd/{id}")
	public Map<String, String> deleteUser(@PathVariable("id") int id, @RequestBody User user) {
		int rowsAffected = userDao.deleteUser(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "User Deleted Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}
}
