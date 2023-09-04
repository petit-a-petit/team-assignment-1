package com.example.assignment1.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class PageResponse {

	@Getter
	@Builder
	public static class FindDto {
		private String title;
		private String content;
		private String breadcrumbs;
		private List<String> subPages;
	}

}
