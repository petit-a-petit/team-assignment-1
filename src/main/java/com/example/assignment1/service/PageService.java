package com.example.assignment1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.assignment1.dto.PageRequest;
import com.example.assignment1.dto.PageResponse;
import com.example.assignment1.model.Page;
import com.example.assignment1.persistence.PageRepository;

@Service
public class PageService {
	@Autowired
	private PageRepository pageRepository;

	@Transactional
	public void createPage(PageRequest.CreateDto createDto) {
		// 새로운 페이지 생성
		Page newPage = new Page();
		newPage.setTitle(createDto.getTitle());
		newPage.setContent(createDto.getContent());

		// 부모 페이지가 있는 경우
		String prefix = "";
		if (createDto.getParentPageId() != null) {
			newPage.setParentPageId(createDto.getParentPageId());
			Page parentPage = pageRepository.findById(createDto.getParentPageId());
			prefix = parentPage.getBreadcrumbs(); // 부모 페이지의 브레드크럼 정보 가져오기
		}
		// 새 페이지의 브레드크럼 정보 설정
		newPage.updateBreadcrumbs(prefix + " / " + newPage.getTitle());

		// 페이지 저장
		pageRepository.save(newPage);
	}

	@Transactional
	public PageResponse.FindDto getPageInfo(Long pageId) {
		Page page = pageRepository.findById(pageId);
		if (page == null) {
			throw new RuntimeException("Page not found with ID: " + pageId);
		}

		return PageResponse.FindDto.builder()
			.title(page.getTitle())
			.content(page.getContent())
			.parentPageId(page.getParentPageId())
			.breadcrumbs(page.getBreadcrumbs())
			.subPages(pageRepository.findByParentPageId(page.getId()))
			.build();
	}

}
