package com.example.assignment1.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PageResponse {

	@Getter
	@Builder
	public static class CreateDto {
		private String title;
		private String content;
	}

}
