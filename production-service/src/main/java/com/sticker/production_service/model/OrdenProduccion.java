package com.sticker.production_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_produccion")
public class OrdenProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    @Column(name = "diseno_url", length = 255)
    private String disenoUrl;

    @Column(name = "estado_produccion", nullable = false)
    private String estadoProduccion = "PENDIENTE";

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio = LocalDateTime.now();

    @Column(name = "fecha_termino")
    private LocalDateTime fechaTermino;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public String getDisenoUrl() { return disenoUrl; }
    public void setDisenoUrl(String disenoUrl) { this.disenoUrl = disenoUrl; }

    public String getEstadoProduccion() { return estadoProduccion; }
    public void setEstadoProduccion(String estadoProduccion) { this.estadoProduccion = estadoProduccion; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaTermino() { return fechaTermino; }
    public void setFechaTermino(LocalDateTime fechaTermino) { this.fechaTermino = fechaTermino; }
}