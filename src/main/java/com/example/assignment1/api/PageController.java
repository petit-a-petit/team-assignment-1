package com.example.assignment1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<PageResponse.CreateResponse> createPage(@RequestBody PageRequest.CreateRequest createRequest) {
		PageResponse.CreateResponse createResponse = pageService.createPage(createRequest);
		return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
	}

	// 페이지 정보 조회 (브레드크럼, 서브페이지)
	@GetMapping("/{pageId}")
	public ResponseEntity<PageResponse.FindResponse> getPageInfo(@PathVariable Long pageId) {
		PageResponse.FindResponse findResponse = pageService.getPageInfo(pageId);
		return new ResponseEntity<>(findResponse, HttpStatus.OK);
	}
}
