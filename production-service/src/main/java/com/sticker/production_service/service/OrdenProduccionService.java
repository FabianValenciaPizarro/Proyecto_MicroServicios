package com.sticker.production_service.service;

import com.sticker.production_service.dto.OrdenProduccionDTO;
import com.sticker.production_service.model.OrdenProduccion;
import com.sticker.production_service.repository.OrdenProduccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenProduccionService {

    private static final Logger log = LoggerFactory.getLogger(OrdenProduccionService.class);
    private final OrdenProduccionRepository ordenRepository;

    public OrdenProduccionService(OrdenProduccionRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    public List<OrdenProduccion> listarTodos() {
        log.info("Listando todas las ordenes de produccion");
        return ordenRepository.findAll();
    }

    public Optional<OrdenProduccion> buscarPorId(Long id) {
        log.info("Buscando orden con id: {}", id);
        return ordenRepository.findById(id);
    }

    public List<OrdenProduccion> buscarPorPedido(Long pedidoId) {
        log.info("Buscando ordenes del pedido: {}", pedidoId);
        return ordenRepository.findByPedidoId(pedidoId);
    }

    public List<OrdenProduccion> buscarPorEstado(String estado) {
        log.info("Buscando ordenes con estado: {}", estado);
        return ordenRepository.findByEstadoProduccion(estado);
    }

    public OrdenProduccion crear(OrdenProduccionDTO dto) {
        log.info("Creando orden de produccion para pedido: {}", dto.getPedidoId());

        OrdenProduccion orden = new OrdenProduccion();
        orden.setPedidoId(dto.getPedidoId());
        orden.setDisenoUrl(dto.getDisenoUrl());
        orden.setEstadoProduccion("PENDIENTE");

        OrdenProduccion guardada = ordenRepository.save(orden);
        log.info("Orden creada con id: {}", guardada.getId());
        return guardada;
    }

    public OrdenProduccion actualizarEstado(Long id, String estado) {
        log.info("Actualizando estado de orden: {}", id);
        OrdenProduccion orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setEstadoProduccion(estado);

        if (estado.equals("TERMINADO")) {
            orden.setFechaTermino(LocalDateTime.now());
            log.info("Orden {} terminada", id);
        }

        return ordenRepository.save(orden);
    }

    public void eliminar(Long id) {
        log.info("Eliminando orden con id: {}", id);
        ordenRepository.deleteById(id);
    }
}