package com.example.teamassignment1.entity;

import lombok.Getter;

@Getter
public class Page {
    private Long id;
    private String title;
    private Long parentId;

    public Page(long pageId, String title, long parentId) {
        this.id = pageId;
        this.title = title;
        this.parentId = parentId;
    }
}
