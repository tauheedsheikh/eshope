package com.eshope.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshope.webservice.modul.Product;

@Repository
public class ProductDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class ProductRowMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt(1));
			product.setName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			product.setDescreption(rs.getString(4));
			product.setCreatedAt(rs.getDate(5));

			return product;
		}
	}

	public List<Product> getAllProduct() {
		List<Product> product = new LinkedList<Product>();
		product = jdbcTemplate.query("select * from products", new ProductRowMapper());
		return product;

	}

	public Product findbyId(int id) {
		return jdbcTemplate.queryForObject("select * from products where id=?", new ProductRowMapper(), id);
	}

	public Product findByName(String name) {
		return jdbcTemplate.queryForObject("select * from products where name=?", new ProductRowMapper(), name);
	}

	public int insertProduct(Product product) {
		return jdbcTemplate.update("insert into products (id,name,price,descreption)" + "values(?,?,?,?)",
				new Object[] { product.getId(), product.getName(), product.getPrice(), product.getDescreption() });

	}

	public int updateProduct(Product product) {
		return jdbcTemplate.update("update products " + "set name=? ,price=?,descreption=?" + "where id=?",
				new Object[] { product.getName(), product.getPrice(), product.getDescreption(), product.getId() });
	}

	public int delete(int id) {
		return jdbcTemplate.update("delete from products where id= ?", id);
	}
}
