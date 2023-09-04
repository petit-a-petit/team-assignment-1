package com.example.assignment1.service;

import java.util.List;

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
	public PageResponse.CreateResponse createPage(PageRequest.CreateRequest createRequest) {
		Page newPage = new Page();
		newPage.setTitle(createRequest.getTitle());
		newPage.setContent(createRequest.getContent());

		String prefix = "";
		if (createRequest.getParentPageId() != null) {
			newPage.setParentPageId(createRequest.getParentPageId());
			Page parentPage = pageRepository.findById(createRequest.getParentPageId());
			prefix = parentPage.getBreadcrumbs(); // 부모 페이지의 브레드크럼 정보 가져오기
		}
		newPage.updateBreadcrumbs(prefix + " / " + newPage.getTitle()); // 새 페이지의 브레드크럼 정보 설정

		Long pageId = pageRepository.save(newPage);

		return PageResponse.CreateResponse.builder()
			.id(pageId)
			.title(newPage.getTitle())
			.content(newPage.getContent())
			.breadcrumbs(newPage.getBreadcrumbs())
			.parentPageId(newPage.getParentPageId())
			.build();
	}

	@Transactional
	public PageResponse.FindResponse getPageInfo(Long pageId) {
		Page page = pageRepository.findById(pageId);
		if (page == null) {
			throw new RuntimeException("Page not found with ID: " + pageId);
		}

		List<String> subPages = pageRepository.findByParentPageId(page.getId());

		return PageResponse.FindResponse.builder()
			.title(page.getTitle())
			.content(page.getContent())
			.breadcrumbs(page.getBreadcrumbs())
			.subPages(subPages)
			.build();
	}

}
