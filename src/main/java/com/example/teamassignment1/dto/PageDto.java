package com.example.teamassignment1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto {
    private long id;
    private String title;
    private List<String> parents;
    private List<Long> children;

    public PageDto(long pageId, String title) {
        this.id = pageId;
        this.title = title;
    }

    public PageDto(){

    }

    public PageDto(Long id, String title, List<String> parentList, List<Long> childrenList) {
        this.id = id;
        this.title = title;
        this.parents = parentList;
        this.children = childrenList;
    }
}

