CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    material_id BIGINT NOT NULL,
    ancho_cm DOUBLE NOT NULL,
    alto_cm DOUBLE NOT NULL,
    cantidad INT NOT NULL,
    monto_total DOUBLE NOT NULL,
    estado_pago VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);