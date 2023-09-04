package com.example.assignment1.dto;

import lombok.Builder;
import lombok.Getter;

public class PageResponse {

	@Getter
	@Builder
	public static class CreateDto {
		private String title;
		private String content;
		private Long parentPageId;
		private String breadcrumbs;
		// private List<Page> childPages;
		// private List<String> breadcrumbs = new ArrayList<>();
	}

}
