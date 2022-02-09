package com.eshope.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshope.webservice.modul.User;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setEmail(rs.getString(3));
			user.setPassword(rs.getString(4));
			user.setPhone(rs.getString(5));
			return user;
		}
	}

	public List<User> getAllUser() {
		List<User> user = new LinkedList<User>();
		user = jdbcTemplate.query("select * from users", new UserMapper());
		return user;
	}

	public User getUserByName(String name) {
		return jdbcTemplate.queryForObject("select * from users where name=?", new UserMapper(), name);
	}

	public User loginUser(String email) {
		return jdbcTemplate.queryForObject("select * from users where email=?", new UserMapper(), email);
	}
	
	public User getUserById(int id) {
		return jdbcTemplate.queryForObject("select * from users where id=?", new UserMapper(), id);
	}

	public int insertUser(User user) {
		return jdbcTemplate.update("insert into users (id,name,email,password,phone)" + "values(?,?,?,?,?)",
				new Object[] { user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getPhone() });
	}

	public int updateUser(User user) {
		return jdbcTemplate.update("update users " + "set name=?, email=?,password=?,phone=?" + "where id=?",
				new Object[] { user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getId()});
	}

	public int deleteUser(int id) {
		return jdbcTemplate.update("delete from user where id=?", id);
	}
}
