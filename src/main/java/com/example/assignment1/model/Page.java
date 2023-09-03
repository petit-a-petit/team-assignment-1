package com.example.assignment1.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Page {
	private Long id;
	private String title;
	private String content;
	// private Long parentPageId;
	// private List<Page> childPages;
	// private Page parentPage;

	@Builder
	private Page(final String title, final String content) {
		this.title = title;
		this.content = content;
	}

	public static Page of(final String title, final String content) {
		return Page.builder()
			.title(title)
			.content(content)
			.build();
	}
}
