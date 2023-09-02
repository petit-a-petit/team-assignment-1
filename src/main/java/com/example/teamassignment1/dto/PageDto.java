package com.example.teamassignment1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {
    private long id;
    private String title;
    private long parentId;

    public PageDto(long id, String title, long parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
    }
}

