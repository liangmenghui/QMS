package com.unind.base.dbconnection.dao.internal;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.unind.base.dbconnection.query.SQLParameter;

@Repository
public class JdbcDao extends NamedParameterJdbcTemplate {
	@Autowired
	protected DataSource dataSource;

	public JdbcDao(DataSource dataSource) {
		super(dataSource);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public List<Map<String, Object>> find(String sql, SQLParameter<Object> parameters) {
		return jdbcTemplate.queryForList(sql, parameters.toArray(new Object[]{}));
	}

}
