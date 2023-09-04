package com.example.teamassignment1.controller;

import com.example.teamassignment1.dto.PageDto;
import com.example.teamassignment1.service.PageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/page/cte/{id}")
    public PageDto getPageWithCte(@PathVariable Long id) {
        PageDto page = pageService.getPageByIdWithCte(id);

        return page;
    }

    @GetMapping("/page/{id}")
    public PageDto getPage(@PathVariable Long id) {
        PageDto page = pageService.getPageById(id);

        return page;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPage(@RequestParam(required = false) Long parentId,
                                             @RequestParam(required = false) String title) {
        if (parentId == null){
            parentId = 1L;
        }
        pageService.createPage(title, parentId);

        return ResponseEntity.ok().body("");
    }

}
