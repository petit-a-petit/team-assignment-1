package com.example.assignment1.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Page {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createAt;

	@Builder
	private Page(final String title, final String content, LocalDateTime createAt) {
		this.title = title;
		this.content = content;
		this.createAt = createAt;
	}

	public static Page of(final String title, final String content) {
		return Page.builder()
			.title(title)
			.content(content)
			.createAt(LocalDateTime.now())
			.build();
	}
}
