package com.sticker.production_service.dto;

import jakarta.validation.constraints.NotNull;

public class OrdenProduccionDTO {

    @NotNull(message = "El pedido es obligatorio")
    private Long pedidoId;

    private String disenoUrl;
    private String estadoProduccion;

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public String getDisenoUrl() { return disenoUrl; }
    public void setDisenoUrl(String disenoUrl) { this.disenoUrl = disenoUrl; }

    public String getEstadoProduccion() { return estadoProduccion; }
    public void setEstadoProduccion(String estadoProduccion) { this.estadoProduccion = estadoProduccion; }
}