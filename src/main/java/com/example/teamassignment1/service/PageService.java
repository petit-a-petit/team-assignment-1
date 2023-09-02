package com.example.teamassignment1.service;

import com.example.teamassignment1.dto.PageDto;
import com.example.teamassignment1.repository.PageRepository;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public PageDto getPageById(Long id) {
        return pageRepository.getPageById(id);
    }

    public void createPage(String title, long parentId) {
        pageRepository.savePage(title, parentId);
    }
}
