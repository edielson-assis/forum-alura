CREATE TABLE topic (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255),
    message TEXT,
    moment TIMESTAMP,
    status_topic ENUM('NAO_RESPONDIDO', 'NAO_SOLUCIONADO', 'SOLUCIONADO', 'FECHADO'),
    author_id BIGINT,
    course_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);