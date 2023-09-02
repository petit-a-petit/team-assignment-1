package com.example.teamassignment1.repository;

import com.example.teamassignment1.dto.PageDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PageRepository {
    private final JdbcTemplate jdbcTemplate;

    public PageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PageDto getPageById(Long id) {
        String sql = "SELECT * FROM pages WHERE page_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            PageDto page = new PageDto(rs.getLong("page_id"),
                    rs.getString("title"),
                    rs.getLong("parent_id"));

            return page;
        });
    }

    // 페이지 저장 메서드
    public void savePage(String title, long parentId) {
        String sql = "INSERT INTO pages (title, parent_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, title, parentId);
    }
}
