package com.sticker.catalog_service.repository;

import com.sticker.catalog_service.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    boolean existsByNombreMaterial(String nombreMaterial);
}
