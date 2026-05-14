package com.sticker.notification_service.controller;

import com.sticker.notification_service.dto.NotificacionDTO;
import com.sticker.notification_service.model.Notificacion;
import com.sticker.notification_service.service.NotificacionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private static final Logger log = LoggerFactory.getLogger(NotificacionController.class);
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() {
        log.info("GET /api/notificaciones");
        return ResponseEntity.ok(notificacionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/notificaciones/{}", id);
        return notificacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> buscarPorUsuario(@PathVariable Long usuarioId) {
        log.info("GET /api/notificaciones/usuario/{}", usuarioId);
        return ResponseEntity.ok(notificacionService.buscarPorUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public ResponseEntity<List<Notificacion>> buscarNoLeidas(@PathVariable Long usuarioId) {
        log.info("GET /api/notificaciones/usuario/{}/no-leidas", usuarioId);
        return ResponseEntity.ok(notificacionService.buscarNoLeidas(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Notificacion> enviar(@Valid @RequestBody NotificacionDTO dto) {
        log.info("POST /api/notificaciones");
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.enviar(dto));
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<Notificacion> marcarLeido(@PathVariable Long id) {
        log.info("PUT /api/notificaciones/{}/leido", id);
        return ResponseEntity.ok(notificacionService.marcarLeido(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/notificaciones/{}", id);
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}