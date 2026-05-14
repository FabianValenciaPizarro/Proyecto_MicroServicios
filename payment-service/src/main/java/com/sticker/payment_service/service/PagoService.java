package com.sticker.payment_service.service;

import com.sticker.payment_service.dto.PagoDTO;
import com.sticker.payment_service.model.Pago;
import com.sticker.payment_service.repository.PagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> listarTodos() {
        log.info("Listando todos los pagos");
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(Long id) {
        log.info("Buscando pago con id: {}", id);
        return pagoRepository.findById(id);
    }

    public List<Pago> buscarPorPedido(Long pedidoId) {
        log.info("Buscando pagos del pedido: {}", pedidoId);
        return pagoRepository.findByPedidoId(pedidoId);
    }

    public Pago procesar(PagoDTO dto) {
        log.info("Procesando pago para pedido: {}", dto.getPedidoId());

        Pago pago = new Pago();
        pago.setPedidoId(dto.getPedidoId());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setMonto(dto.getMonto());
        pago.setTransaccionId(UUID.randomUUID().toString());
        pago.setEstado("PAGADO");

        Pago guardado = pagoRepository.save(pago);
        log.info("Pago procesado con id: {}", guardado.getId());
        return guardado;
    }

    public Pago actualizarEstado(Long id, String estado) {
        log.info("Actualizando estado del pago: {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pago.setEstado(estado);
        return pagoRepository.save(pago);
    }
}