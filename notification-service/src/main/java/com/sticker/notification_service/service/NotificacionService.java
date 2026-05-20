package com.sticker.notification_service.service;

import com.sticker.notification_service.dto.NotificacionDTO;
import com.sticker.notification_service.model.Notificacion;
import com.sticker.notification_service.repository.NotificacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);
    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public List<Notificacion> listarTodos() {
        log.info("Listando todas las notificaciones");
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> buscarPorId(Long id) {
        log.info("Buscando notificacion con id: {}", id);
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> buscarPorUsuario(Long usuarioId) {
        log.info("Buscando notificaciones del usuario: {}", usuarioId);
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> buscarNoLeidas(Long usuarioId) {
        log.info("Buscando notificaciones no leidas del usuario: {}", usuarioId);
        return notificacionRepository.findByUsuarioIdAndLeido(usuarioId, false);
    }

    public Notificacion enviar(NotificacionDTO dto) {
        log.info("Enviando notificacion a usuario: {}", dto.getUsuarioId());

        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(dto.getUsuarioId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipo(dto.getTipo() != null ? dto.getTipo() : "Email");
        notificacion.setLeido(false);

        Notificacion guardada = notificacionRepository.save(notificacion);
        log.info("Notificacion enviada con id: {}", guardada.getId());
        return guardada;
    }

    public Notificacion marcarLeido(Long id) {
        log.info("Marcando notificacion {} como leida", id);
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
        notificacion.setLeido(true);
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(Long id) {
        log.info("Eliminando notificacion con id: {}", id);
        notificacionRepository.deleteById(id);
    }
}