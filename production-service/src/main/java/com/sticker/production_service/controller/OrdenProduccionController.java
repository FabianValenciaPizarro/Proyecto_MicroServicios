package com.sticker.production_service.controller;

import com.sticker.production_service.dto.OrdenProduccionDTO;
import com.sticker.production_service.model.OrdenProduccion;
import com.sticker.production_service.service.OrdenProduccionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produccion")
public class OrdenProduccionController {

    private static final Logger log = LoggerFactory.getLogger(OrdenProduccionController.class);
    private final OrdenProduccionService ordenService;

    public OrdenProduccionController(OrdenProduccionService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<List<OrdenProduccion>> listar() {
        log.info("GET /api/produccion");
        return ResponseEntity.ok(ordenService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenProduccion> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/produccion/{}", id);
        return ordenService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<OrdenProduccion>> buscarPorPedido(@PathVariable Long pedidoId) {
        log.info("GET /api/produccion/pedido/{}", pedidoId);
        return ResponseEntity.ok(ordenService.buscarPorPedido(pedidoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenProduccion>> buscarPorEstado(@PathVariable String estado) {
        log.info("GET /api/produccion/estado/{}", estado);
        return ResponseEntity.ok(ordenService.buscarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<OrdenProduccion> crear(@Valid @RequestBody OrdenProduccionDTO dto) {
        log.info("POST /api/produccion");
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crear(dto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<OrdenProduccion> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        log.info("PUT /api/produccion/{}/estado", id);
        return ResponseEntity.ok(ordenService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/produccion/{}", id);
        ordenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}