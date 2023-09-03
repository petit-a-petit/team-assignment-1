package com.example.assignment1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment1.dto.PageRequest;
import com.example.assignment1.dto.PageResponse;
import com.example.assignment1.model.Page;
import com.example.assignment1.persistence.PageRepository;

@Service
public class PageService {
	@Autowired
	private PageRepository pageRepository;

	public PageResponse.CreateDto createPage(PageRequest.CreateDto createDto) {
		Page page = Page.of(createDto.getTitle(), createDto.getContent());
		pageRepository.save(page);

		return PageResponse.CreateDto.builder()
			.title(page.getTitle())
			.content(page.getContent())
			.createAt(page.getCreateAt())
			.build();
	}
}
