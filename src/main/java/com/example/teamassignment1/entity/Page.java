package com.example.teamassignment1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Page {
    private long id;
    private String title;
    private long parentId;

    private Page(long pageId, String title, long parentId) {
        this.id = pageId;
        this.title = title;
        this.parentId = parentId;
    }
    public static Page from(ResultSet rs) {
        Page page;
        try {
            page = new Page(
                    rs.getLong("page_id"),
                    rs.getString("title"),
                    rs.getLong("parent_id")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return page;
    }
}
