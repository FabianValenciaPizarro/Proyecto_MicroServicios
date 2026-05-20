package com.sticker.catalog_service.controller;

import com.sticker.catalog_service.dto.MaterialDTO;
import com.sticker.catalog_service.model.Material;
import com.sticker.catalog_service.service.MaterialService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private static final Logger log = LoggerFactory.getLogger(MaterialController.class);
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<Material>> listar() {
        log.info("GET /api/materiales");
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/materiales/{}", id);
        return materialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Material> crear(@Valid @RequestBody MaterialDTO dto) {
        log.info("POST /api/materiales");
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> actualizar(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
        log.info("PUT /api/materiales/{}", id);
        return ResponseEntity.ok(materialService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/materiales/{}", id);
        materialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}