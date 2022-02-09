package com.eshope.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshope.webservice.modul.Rolls;

@Repository
public class RollDao {

	@Autowired
	JdbcTemplate template;

	class RollsMapper implements RowMapper<Rolls> {
		@Override
		public Rolls mapRow(ResultSet rs, int rowNum) throws SQLException {
			Rolls rolls = new Rolls();
			rolls.setId(rs.getInt(1));
			rolls.setRollName(rs.getString(2));
			rolls.setSalary(rs.getDouble(3));
			rolls.setCreatedAt(rs.getDate(4));
			return rolls;
		}
	}

	public List<Rolls> getAllRolls() {
		List<Rolls> roll = new LinkedList<Rolls>();
		roll=template.query("select * from rolls", new RollsMapper());
		return roll;
	}

	public Rolls getRollByName(String name) {
		return template.queryForObject("select * from rolls where rollName=?", new RollsMapper(), name);

	}

	public Rolls getRollById(int id) {
		return template.queryForObject("select * from rolls where id=?", new RollsMapper(), id);
	}

	public int insertRoll(Rolls rolls) {
		return template.update("insert into rolls (id,rollName,salary)" + "values(?,?,?)",
				new Object[] { rolls.getId(), rolls.getRollName(),rolls.getSalary()});
	}

	public int updateRolls(Rolls rolls) {
		return template.update("update rolls " + "set rollName=?,salary=?" + "where id=?",
				new Object[] {  rolls.getRollName(), rolls.getSalary(),rolls.getId()});
	}

	public int deleteRoll(int id) {
		return template.update("delete from rolls where id=?", id);

	}

}
