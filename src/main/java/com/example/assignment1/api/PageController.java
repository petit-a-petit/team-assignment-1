package com.example.assignment1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<PageResponse.CreateDto> createPage(@RequestBody PageRequest.CreateDto pageRequest) {
		PageResponse.CreateDto pageResponse = pageService.createPage(pageRequest);
		return new ResponseEntity<>(pageResponse, HttpStatus.CREATED);
	}
}
