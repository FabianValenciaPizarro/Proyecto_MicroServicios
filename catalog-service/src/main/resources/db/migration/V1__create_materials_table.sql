-- Create materials table
CREATE TABLE IF NOT EXISTS materials (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    price_per_unit DECIMAL(10, 2) NOT NULL,
    unit_type VARCHAR(50),
    stock_quantity INT DEFAULT 0,
    min_quantity INT DEFAULT 1,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_name ON materials(name);
CREATE INDEX idx_is_active ON materials(is_active);