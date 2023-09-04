package com.example.assignment1.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Page {
	private Long id;
	private String title;
	private String content;
	private Long parentPageId;
	private String breadcrumbs; // 현재 페이지 경로 (부모 페이지들)

	public void updateBreadcrumbs(String breadcrumb) {
		if (breadcrumbs == null) {
			breadcrumbs = breadcrumb;
		} else {
			breadcrumbs += " / " + breadcrumb;
		}
	}

	public void updateParentPageId(Long parentPageId) {
		this.parentPageId = parentPageId;
	}
}
