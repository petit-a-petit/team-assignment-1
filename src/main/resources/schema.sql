CREATE TABLE pages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    parent_page_id BIGINT,
    breadcrumbs VARCHAR(255),
    FOREIGN KEY (parent_page_id) REFERENCES pages(id)
);
