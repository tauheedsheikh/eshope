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

import com.eshope.webservice.dao.OrderDao;
import com.eshope.webservice.exception.OrderNotFound;
import com.eshope.webservice.modul.Order;

@RestController
public class OrderController {

	@Autowired
	OrderDao orderDao;

	@GetMapping("/orders")
	public Order getProductByName(@RequestParam("name") String name) {
		Order order = orderDao.getOrderByName(name);
		if (order != null) {
			return order;
		}
		throw new OrderNotFound("Order Not Found With the given Name " + name);
	}

	@GetMapping("/order/{id}")
	public Order getProductByName(@PathVariable("id") int id) {
		Order order = orderDao.getOrderById(id);
		if (order != null) {
			return order;
		}
		throw new OrderNotFound("Order Not Found With the given Name " + id);

	}

	@GetMapping("/orderss")
	public List<Order> getAllOrder() {
		List<Order> list = orderDao.getAllOrder();
		if (list.isEmpty()) {
			throw new OrderNotFound("Orders Not Found With the Given Information");

		}
		return list;
	}

	@PostMapping("/orderu")
	public Map<String, String> addOrders(@RequestBody Order order) {
		int rowsAffected = orderDao.insertOrder(order);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Order created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/orders/{id}")
	public Map<String, String> updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
		int rowsAffected = orderDao.updateOrder(order);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Order created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@DeleteMapping("/orderd/{id}")
	public Map<String, String> deleteOrder(@PathVariable("id") int id) {
		int rowsAffected = orderDao.deleteOrder(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Order created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}
}