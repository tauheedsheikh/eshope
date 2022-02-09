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

import com.eshope.webservice.dao.RollDao;
import com.eshope.webservice.exception.RollNotFound;
import com.eshope.webservice.modul.Rolls;

@RestController
public class RollsController {

	@Autowired
	RollDao dao;

	@GetMapping("/roll")
	public Rolls getRollByName(@RequestParam("name") String name) {
		Rolls roll = dao.getRollByName(name);
		if (roll != null) {
			return roll;
		}
		throw new RollNotFound("Rolls Not Found With the given Name " + name);
	}

	@GetMapping("/rolla")
	public Rolls getRollById(@PathVariable("id") int id) {
		Rolls roll = dao.getRollById(id);
		if (roll != null) {
			return roll;
		}
		throw new RollNotFound("Rolls Not Found With the given Name " + id);
	}

	@GetMapping("/roolls")
	public List<Rolls> getAllRolls() {
		List<Rolls> list = dao.getAllRolls();
		if (list.isEmpty()) {
			throw new RollNotFound("Rolls Not Found With the given Information");

		}
		return list;

	}

	@PostMapping("/rools")
	public Map<String, String> addRolls(@RequestBody Rolls rolls) {
		int rowsAffected = dao.insertRoll(rolls);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Rolls created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/roolls/{id}")
	public Map<String, String> updateRollsById(@PathVariable("id") int id, @RequestBody Rolls rolls) {
		int rowsAffected = dao.updateRolls(rolls);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Rolls created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@DeleteMapping("/rolls/{id}")
	public Map<String, String> deleteRolls(@PathVariable("id") int id) {
		int rowsAffected = dao.deleteRoll(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Rolls created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

}
