package com.sticker.production_service.repository;

import com.sticker.production_service.model.OrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Long> {
    List<OrdenProduccion> findByPedidoId(Long pedidoId);
    List<OrdenProduccion> findByEstadoProduccion(String estadoProduccion);
}