package com.example.assignment1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment1.dto.PageRequest;
import com.example.assignment1.dto.PageResponse;
import com.example.assignment1.service.PageService;

@RestController
@RequestMapping("/pages")
public class PageController {
	@Autowired
	private PageService pageService;

	@PostMapping()
	public ResponseEntity<PageResponse.FindDto> createPage(@RequestBody PageRequest.CreateDto pageRequest) { // todo: 반환타입 수정
		pageService.createPage(pageRequest);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	// 페이지 정보 조회 (브레드크럼, 서브페이지)
	@GetMapping()
	public ResponseEntity<PageResponse.FindDto> getPageInfo(@RequestParam Long pageId) {
		PageResponse.FindDto pageResponse = pageService.getPageInfo(pageId);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);
	}
}
