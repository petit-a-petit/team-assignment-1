package com.example.teamassignment1.dto;

import com.example.teamassignment1.entity.Page;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDto {
    private long id;
    private String title;
    private List<String> parents;
    private List<Long> children;

    private PageDto(Long id, String title, List<String> parentList, List<Long> childrenList) {
        this.id = id;
        this.title = title;
        this.parents = parentList;
        this.children = childrenList;
    }

    public static PageDto createPageDto(Page page, List<String> parentList, List<Long> childrenList){
        PageDto pageDto = new PageDto(page.getId(), page.getTitle(), parentList,childrenList);

        return pageDto;
    }
}

