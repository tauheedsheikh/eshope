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

import com.eshope.webservice.dao.ProductDao;
import com.eshope.webservice.exception.ProductNotFound;
import com.eshope.webservice.modul.Product;

@RestController
public class ProductController {

	@Autowired
	ProductDao dao;

	@GetMapping("/products")
	public Product getProductByName(@RequestParam("name") String name) {
		Product product = dao.findByName(name);
		if (product != null) {
			return product;
		}
		throw new ProductNotFound("Product Not Found With the given Name " + name);
	}

	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		Product product = dao.findbyId(id);
		if (product != null) {
			return product;
		}
		throw new ProductNotFound("Product Not Found With the given Name " + id);

	}

	@GetMapping("/product")
	public List<Product> getProduct() {
		List<Product> list = dao.getAllProduct();
		if (list.isEmpty()) {
			throw new ProductNotFound("Product Not Found with the Given Information");

		}
		return list;
	}

	@PostMapping("/productu")
	public Map<String, String> addProduct(@RequestBody Product product) {
		int rowsAffected = dao.insertProduct(product);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Product created Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/product/{id}")
	public Map<String, String> updateProductById(@PathVariable("id") int id, @RequestBody Product product) {
		int rowsAffected = dao.updateProduct(product);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Product is updated Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@DeleteMapping("/productss/{id}")
	public Map<String, String> deleteProduct(@PathVariable("id") int id) {
		int rowsAffected = dao.delete(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Product Delete Successfully");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;

	}
}