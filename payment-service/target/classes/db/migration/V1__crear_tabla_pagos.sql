CREATE TABLE pagos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       pedido_id BIGINT NOT NULL,
                       metodo_pago VARCHAR(50) NOT NULL,
                       monto DOUBLE NOT NULL,
                       estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                       transaccion_id VARCHAR(100),
                       fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
