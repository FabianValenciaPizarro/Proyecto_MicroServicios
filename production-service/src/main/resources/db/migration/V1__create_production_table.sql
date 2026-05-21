-- Create production table
CREATE TABLE IF NOT EXISTS production_orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL UNIQUE,
    plotter_assigned VARCHAR(50),
    status VARCHAR(50) DEFAULT 'NOT_STARTED',
    priority VARCHAR(20) DEFAULT 'NORMAL',
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    estimated_completion_time INT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_order_id ON production_orders(order_id);
CREATE INDEX idx_status ON production_orders(status);
CREATE INDEX idx_plotter ON production_orders(plotter_assigned);