package com.eshope.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshope.webservice.modul.Order;

@Repository
public class OrderDao {

	@Autowired
	JdbcTemplate template;

	class OrderMapper implements RowMapper<Order> {

		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getInt(1));
			order.setName(rs.getString(2));
			order.setPrice(rs.getDouble(3));
			order.setDescription(rs.getString(4));
			order.setCreatedAt(rs.getDate(5));
			return order;
		}
	}

	public List<Order> getAllOrder() {
		List<Order> order = new LinkedList<Order>();
		order = template.query("select * from orders", new OrderMapper());
		return order;
	}

	public Order getOrderByName(String name) {
		return template.queryForObject("select * from orders where name=?", new OrderMapper(), name);
	}

	public Order getOrderById(int id) {
		return template.queryForObject("select * from orders where id=?", new OrderMapper(), id);

	}

	public int insertOrder(Order order) {
		return template.update("insert into orders (id,name,price,description)" + "values(?,?,?,?)",
				new Object[] { order.getId(), order.getName(), order.getPrice(), order.getDescription() });

	}

	public int updateOrder(Order order) {
		return template.update("update orders " + "set name=?,price=?,description=?" + "where id=?",
				new Object[] { order.getId(), order.getName(), order.getDescription() });
	}

	public int deleteOrder(int id) {
		return template.update("delete from orders where id=?", id);
	}
}
