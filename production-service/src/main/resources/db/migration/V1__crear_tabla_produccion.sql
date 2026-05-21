CREATE TABLE ordenes_produccion (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    pedido_id BIGINT NOT NULL,
                                    diseno_url VARCHAR(255),
                                    estado_produccion VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                                    fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    fecha_termino TIMESTAMP NULL
);