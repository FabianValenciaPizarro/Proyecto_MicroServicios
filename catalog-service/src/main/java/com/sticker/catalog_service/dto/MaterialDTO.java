package com.sticker.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MaterialDTO {

    @NotBlank(message = "El nombre del material es obligatorio")
    private String nombreMaterial;

    @NotNull(message = "El precio por cm2 es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precioPorCm2;

    @NotNull(message = "El stock es obligatorio")
    private Double stockActual;

    private String descripcion;

    public String getNombreMaterial() { return nombreMaterial; }
    public void setNombreMaterial(String nombreMaterial) { this.nombreMaterial = nombreMaterial; }

    public Double getPrecioPorCm2() { return precioPorCm2; }
    public void setPrecioPorCm2(Double precioPorCm2) { this.precioPorCm2 = precioPorCm2; }

    public Double getStockActual() { return stockActual; }
    public void setStockActual(Double stockActual) { this.stockActual = stockActual; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}