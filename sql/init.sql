-- init.sql

CREATE TABLE pages (
   page_id INT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   parent_id INT,
   FOREIGN KEY (parent_id) REFERENCES pages(page_id)
);

INSERT INTO pages (title, parent_id) VALUES
                                         ("home",NULL);
