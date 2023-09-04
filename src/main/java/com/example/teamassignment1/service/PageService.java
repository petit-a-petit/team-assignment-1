package com.example.teamassignment1.service;

import com.example.teamassignment1.dto.PageDto;
import com.example.teamassignment1.entity.Page;
import com.example.teamassignment1.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public PageDto getPageByIdWithCte(Long id) {
        Page pageEntity = pageRepository.getPageById(id);
        List<String> parentList = pageRepository.findParentPageTitleByIdWithCte(pageEntity.getId());
        List<Long> childrenList = pageRepository.findChildrenPageById(pageEntity);

        PageDto response = PageDto.createPageDto(pageEntity,parentList,childrenList);

        return response;
    }

    public PageDto getPageById(Long id) {
        Page pageEntity = pageRepository.getPageById(id);
        List<String> parentList = pageRepository.findParentPageTitleById(pageEntity);
        List<Long> childrenList = pageRepository.findChildrenPageById(pageEntity);

        PageDto response = PageDto.createPageDto(pageEntity,parentList,childrenList);

        return response;
    }

    public void createPage(String title, long parentId) {
        pageRepository.savePage(title, parentId);
    }
}
