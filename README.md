# Sticker Microservicios

## Descripción
Plataforma de venta de stickers personalizados basada en arquitectura 
de microservicios con Spring Boot, JPA, Flyway y comunicación entre 
servicios mediante Feign Client.

## Integrantes
- Christian Troncoso
- Elias Jimenez
- Fabian Valencia

## Contexto del Dominio
Sistema de gestión de pedidos de stickers personalizados que permite 
a los clientes solicitar stickers según material, medidas y cantidad, 
calculando el precio automáticamente según el catálogo de materiales.

## Microservicios
| Servicio | Puerto | Descripción |
|---|---|---|
| user-service | 8081 | Gestión de usuarios y roles |
| catalog-service | 8082 | Materiales y precios por cm² |
| order-service | 8083 | Pedidos y cálculo de montos |
| payment-service | 8084 | Procesamiento de pagos |
| production-service | 8085 | Control de taller y plotters |
| notification-service | 8086 | Notificaciones a clientes |

## Tecnologías utilizadas
- Java 21
- Spring Boot 3.5.14
- Spring Data JPA + Hibernate
- Flyway Migration
- MySQL 8
- OpenFeign (comunicación entre microservicios)
- Maven
- Laragon (entorno local)

## Requisitos previos
- Java 21 instalado
- Laragon con MySQL corriendo
- IntelliJ IDEA
- Postman (para pruebas)

## Pasos para ejecutar

### 1. Iniciar base de datos
- Abrir Laragon
- Click en Start All
- Verificar que MySQL esté activo

### 2. Ejecutar microservicios
Abrir cada proyecto en IntelliJ y ejecutar en este orden:
