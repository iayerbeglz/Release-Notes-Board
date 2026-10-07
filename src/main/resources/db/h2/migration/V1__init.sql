CREATE TABLE application (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)  NOT NULL
);

CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    release VARCHAR(12) NOT NULL,
    description VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    application_id BIGINT NOT NULL,
    CONSTRAINT fk_application FOREIGN KEY(application_id) REFERENCES application(id) ON DELETE CASCADE
);