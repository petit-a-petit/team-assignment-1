package com.example.assignment1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PageRequest {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateRequest {
		private String title;
		private String content;
		private Long parentPageId;
	}

}
