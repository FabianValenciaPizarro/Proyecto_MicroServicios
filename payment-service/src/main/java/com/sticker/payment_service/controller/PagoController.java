package com.sticker.payment_service.controller;

import com.sticker.payment_service.dto.PagoDTO;
import com.sticker.payment_service.model.Pago;
import com.sticker.payment_service.service.PagoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private static final Logger log = LoggerFactory.getLogger(PagoController.class);
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        log.info("GET /api/pagos");
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/pagos/{}", id);
        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<Pago>> buscarPorPedido(@PathVariable Long pedidoId) {
        log.info("GET /api/pagos/pedido/{}", pedidoId);
        return ResponseEntity.ok(pagoService.buscarPorPedido(pedidoId));
    }

    @PostMapping
    public ResponseEntity<Pago> procesar(@Valid @RequestBody PagoDTO dto) {
        log.info("POST /api/pagos");
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.procesar(dto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pago> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        log.info("PUT /api/pagos/{}/estado", id);
        return ResponseEntity.ok(pagoService.actualizarEstado(id, estado));
    }
}