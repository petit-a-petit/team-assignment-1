package com.example.teamassignment1.repository;

import com.example.teamassignment1.dto.PageDto;
import com.example.teamassignment1.entity.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class PageRepository {
    private final JdbcTemplate jdbcTemplate;

    public PageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findParentPageTitleByIdWithCte(Long currentPageId){
        String sql = "WITH RECURSIVE PageHierarchy AS ( \n" +
                "   SELECT page_id, title, parent_id FROM pages WHERE page_id = ? \n" + /* 최초실행 */
                "   UNION ALL\n" +
                "   SELECT p.page_id, p.title, p.parent_id FROM pages p \n" + /* 반복할 쿼리  */
                "   INNER JOIN PageHierarchy ph ON ph.parent_id = p.page_id\n" + /* 반복한 쿼리에서 현재 페이지의 부모와 반복한 쿼리의 현재 페이지가 같아야 함 */
                ")\n" +
                "SELECT title FROM PageHierarchy;";

        List<String> parentPageTitles = jdbcTemplate.queryForList(sql, String.class, currentPageId);

        return parentPageTitles;
    }

    public List<Long> findChildrenPageById(Page currentPage) {
        String sql = "SELECT page_id FROM pages WHERE parent_id = ?";
        List<Long> childrenPageIds = jdbcTemplate.queryForList(sql, Long.class, currentPage.getId());

        return childrenPageIds;
    }

    public List<String> findParentPageTitleById(Page currentPage) {
        List<String> parentPageTitle = new ArrayList<>();

        // 현재 페이지 정보 가져오기
        String sql = "SELECT * FROM pages WHERE page_id = ?";

        while (currentPage.getParentId() != 0) {
            parentPageTitle.add(currentPage.getTitle());
            currentPage = jdbcTemplate.queryForObject(sql, new Object[]{currentPage.getParentId()}, (rs, rowNum) -> {
                Page page = new Page(rs.getLong("page_id"),
                        rs.getString("title"),
                        rs.getLong("parent_id"));

                return page;
            });
        }

        // 마지막 root 페이지는 부모페이지가 없음.
        parentPageTitle.add("home");
        Collections.reverse(parentPageTitle);

        return parentPageTitle;
    }

    public Page getPageById(Long id) {
        String sql = "SELECT * FROM pages WHERE page_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Page page = new Page(
                    rs.getLong("page_id"),
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
