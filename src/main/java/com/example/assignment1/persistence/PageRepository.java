package com.example.assignment1.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.assignment1.model.Page;

@Repository
public class PageRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Page page) {
		String sql = "INSERT INTO pages (title, content) VALUES (?, ?)";
		jdbcTemplate.update(sql, page.getTitle(), page.getContent());
	}
}
