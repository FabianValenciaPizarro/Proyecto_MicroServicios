CREATE TABLE materiales (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nombre_material VARCHAR(100) NOT NULL,
                            precio_por_cm2 DOUBLE NOT NULL,
                            stock_actual DOUBLE NOT NULL,
                            descripcion VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);