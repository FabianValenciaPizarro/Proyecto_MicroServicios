package com.sticker.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "catalog-service", url = "http://localhost:8082")
public interface CatalogClient {

    @GetMapping("/api/materiales/{id}")
    Map<String, Object> obtenerMaterial(@PathVariable Long id);
}