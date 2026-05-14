CREATE TABLE notificaciones (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                usuario_id BIGINT NOT NULL,
                                mensaje VARCHAR(255) NOT NULL,
                                tipo VARCHAR(20) NOT NULL DEFAULT 'Email',
                                leido BOOLEAN NOT NULL DEFAULT FALSE,
                                fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);