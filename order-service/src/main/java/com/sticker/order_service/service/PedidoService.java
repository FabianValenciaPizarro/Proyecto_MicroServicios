package com.sticker.order_service.service;

import com.sticker.order_service.client.CatalogClient;
import com.sticker.order_service.dto.PedidoDTO;
import com.sticker.order_service.model.Pedido;
import com.sticker.order_service.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final CatalogClient catalogClient;

    public PedidoService(PedidoRepository pedidoRepository, CatalogClient catalogClient) {
        this.pedidoRepository = pedidoRepository;
        this.catalogClient = catalogClient;
    }

    public List<Pedido> listarTodos() {
        log.info("Listando todos los pedidos");
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        log.info("Buscando pedido con id: {}", id);
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        log.info("Buscando pedidos del usuario: {}", usuarioId);
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Pedido crear(PedidoDTO dto) {
        log.info("Creando pedido para usuario: {}", dto.getUsuarioId());

        Map<String, Object> material = catalogClient.obtenerMaterial(dto.getMaterialId());
        if (material == null) {
            throw new RuntimeException("Material no encontrado");
        }

        Double precioPorCm2 = Double.valueOf(material.get("precioPorCm2").toString());
        Double area = dto.getAnchoCm() * dto.getAltoCm();
        Double montoTotal = area * precioPorCm2 * dto.getCantidad();

        log.info("Monto total calculado: {}", montoTotal);

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(dto.getUsuarioId());
        pedido.setMaterialId(dto.getMaterialId());
        pedido.setAnchoCm(dto.getAnchoCm());
        pedido.setAltoCm(dto.getAltoCm());
        pedido.setCantidad(dto.getCantidad());
        pedido.setMontoTotal(montoTotal);
        pedido.setEstadoPago("PENDIENTE");

        Pedido guardado = pedidoRepository.save(pedido);
        log.info("Pedido creado con id: {}", guardado.getId());
        return guardado;
    }

    public Pedido actualizarEstado(Long id, String estado) {
        log.info("Actualizando estado del pedido: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstadoPago(estado);
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        log.info("Eliminando pedido con id: {}", id);
        pedidoRepository.deleteById(id);
    }
}