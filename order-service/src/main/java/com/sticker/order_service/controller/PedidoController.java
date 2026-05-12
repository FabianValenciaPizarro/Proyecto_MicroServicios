package com.sticker.order_service.controller;

import com.sticker.order_service.dto.PedidoDTO;
import com.sticker.order_service.model.Pedido;
import com.sticker.order_service.service.PedidoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        log.info("GET /api/pedidos");
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/pedidos/{}", id);
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> buscarPorUsuario(@PathVariable Long usuarioId) {
        log.info("GET /api/pedidos/usuario/{}", usuarioId);
        return ResponseEntity.ok(pedidoService.buscarPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@Valid @RequestBody PedidoDTO dto) {
        log.info("POST /api/pedidos");
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(dto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        log.info("PUT /api/pedidos/{}/estado", id);
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/pedidos/{}", id);
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}