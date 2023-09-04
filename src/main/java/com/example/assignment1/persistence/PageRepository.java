package com.example.assignment1.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.assignment1.model.Page;

@Repository
public class PageRepository {
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/wanted-1?serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
	private static final String JDBC_USERNAME = "admin";
	private static final String JDBC_PASSWORD = "admin";

	public void save(Page page) {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
			String sql = "INSERT INTO pages (title, content, parent_page_id, breadcrumbs) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, page.getTitle());
			pstmt.setString(2, page.getContent());
			if (page.getParentPageId() != null) {
				pstmt.setLong(3, page.getParentPageId());
			} else {
				pstmt.setNull(3, Types.BIGINT);
			}
			pstmt.setString(4, page.getBreadcrumbs());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Page findById(Long pageId) {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
			String sql = "SELECT * FROM pages WHERE id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, pageId);
			ResultSet resultSet = pstmt.executeQuery();
			Page page = new Page();
			if (resultSet.next()) {
				page.setId(resultSet.getLong("id"));
				page.setTitle(resultSet.getString("title"));
				page.setContent(resultSet.getString("content"));
				page.setParentPageId(resultSet.getLong("parent_page_id"));
				page.setBreadcrumbs(resultSet.getString("breadcrumbs"));
			}
			return page;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<String> findByParentPageId(Long parentPageId) {
		List<String> titles = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
			String sql = "SELECT title FROM pages WHERE parent_page_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, parentPageId);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				titles.add(resultSet.getString("title"));
			}

			resultSet.close();
			pstmt.close();
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return titles;
	}
}
