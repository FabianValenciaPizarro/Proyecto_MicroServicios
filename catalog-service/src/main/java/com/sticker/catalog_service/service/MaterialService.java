package com.sticker.catalog_service.service;

import com.sticker.catalog_service.dto.MaterialDTO;
import com.sticker.catalog_service.model.Material;
import com.sticker.catalog_service.repository.MaterialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private static final Logger log = LoggerFactory.getLogger(MaterialService.class);
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> listarTodos() {
        log.info("Listando todos los materiales");
        return materialRepository.findAll();
    }

    public Optional<Material> buscarPorId(Long id) {
        log.info("Buscando material con id: {}", id);
        return materialRepository.findById(id);
    }

    public Material crear(MaterialDTO dto) {
        log.info("Creando material: {}", dto.getNombreMaterial());

        if (materialRepository.existsByNombreMaterial(dto.getNombreMaterial())) {
            throw new RuntimeException("El material ya existe");
        }

        Material material = new Material();
        material.setNombreMaterial(dto.getNombreMaterial());
        material.setPrecioPorCm2(dto.getPrecioPorCm2());
        material.setStockActual(dto.getStockActual());
        material.setDescripcion(dto.getDescripcion());

        Material guardado = materialRepository.save(material);
        log.info("Material creado con id: {}", guardado.getId());
        return guardado;
    }

    public Material actualizar(Long id, MaterialDTO dto) {
        log.info("Actualizando material con id: {}", id);
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));

        material.setNombreMaterial(dto.getNombreMaterial());
        material.setPrecioPorCm2(dto.getPrecioPorCm2());
        material.setStockActual(dto.getStockActual());
        material.setDescripcion(dto.getDescripcion());

        return materialRepository.save(material);
    }

    public void eliminar(Long id) {
        log.info("Eliminando material con id: {}", id);
        materialRepository.deleteById(id);
    }
}