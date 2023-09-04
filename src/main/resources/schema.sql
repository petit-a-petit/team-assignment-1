CREATE DATABASE IF NOT EXISTS `wanted-1`; -- 데이터베이스가 없을 때 생성

USE `wanted-1`; -- 데이터베이스 선택

-- 테이블이 존재하지 않으면 테이블 생성
CREATE TABLE IF NOT EXISTS pages (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255),
                                     content TEXT,
                                     parent_page_id BIGINT,
                                     breadcrumbs VARCHAR(255),
                                     FOREIGN KEY (parent_page_id) REFERENCES pages(id)
);
