package com.sticker.catalog_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre_material", nullable = false, length = 100)
    private String nombreMaterial;

    @NotNull
    @Positive
    @Column(name = "precio_por_cm2", nullable = false)
    private Double precioPorCm2;

    @NotNull
    @Column(name = "stock_actual", nullable = false)
    private Double stockActual;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreMaterial() { return nombreMaterial; }
    public void setNombreMaterial(String nombreMaterial) { this.nombreMaterial = nombreMaterial; }

    public Double getPrecioPorCm2() { return precioPorCm2; }
    public void setPrecioPorCm2(Double precioPorCm2) { this.precioPorCm2 = precioPorCm2; }

    public Double getStockActual() { return stockActual; }
    public void setStockActual(Double stockActual) { this.stockActual = stockActual; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}