# 🔐 Spring Security JWT with Roles - Backend API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8-blue.svg)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)](https://www.docker.com/)
[![JWT](https://img.shields.io/badge/JWT-Authentication-ff69b4.svg)](https://jwt.io/)

Este proyecto es un **backend desarrollado en Spring Boot** que implementa autenticación y autorización mediante **JWT (JSON Web Tokens)**, con control de acceso basado en **roles**. Además, se dockerizó para facilitar su despliegue y uso.

---

## ✨ Características principales

- **Autenticación con JWT**: generación y validación de tokens en cada petición
- **Gestión de usuarios con roles**: `ADMIN`, `DEVELOPER`, `MANAGER`, `USER`
- **Control de acceso a endpoints** según rol con `@PreAuthorize`
- **Carga automática de usuarios** de prueba al iniciar la aplicación (bootstrapping)
- **Dockerizado con MySQL y phpMyAdmin** para base de datos y administración
- **API REST para login y registro** con DTOs específicos

---

## 📁 Estructura del proyecto

```
src/
├── controller/          # Controladores REST para autenticación y usuarios
├── service/             # Servicios para lógica de negocio y seguridad
├── jwt/                 # Utilidades para manejo de JWT y filtros de seguridad
├── model/               # Entidades JPA para usuarios y roles
├── dto/                 # Objetos de transferencia de datos para login y registro
├── configuration/       # Configuraciones Spring, incluida la carga inicial de usuarios
└── repository/          # Repositorios JPA para acceso a la base de datos
```

---

## 👥 Carga inicial de usuarios con roles

Al iniciar la aplicación, si no existen usuarios en la base de datos, se crean automáticamente los siguientes usuarios con sus respectivos roles y contraseña `123` (encriptada con BCrypt):

| Usuario   | Contraseña | Rol       |
|-----------|------------|-----------|
| admin     | 123        | ADMIN     |
| dev       | 123        | DEVELOPER |
| manager   | 123        | MANAGER   |
| user      | 123        | USER      |

Esta carga se realiza mediante la clase `DataInitializer` en la carpeta `configuration`:

```java
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                // Creación de usuarios iniciales con roles y contraseñas encriptadas
                // ...
            }
        };
    }
}
```

### 💾 Inserción manual de usuarios en la base de datos (opcional)

Si prefieres agregar usuarios directamente con SQL, aquí están los comandos para insertar los usuarios con contraseñas ya encriptadas con BCrypt:

```sql
-- Contraseña para adminUser: admin456
INSERT INTO user (user_name, password, role) VALUES
('adminUser', '$2a$12$uh87.HEmnViGSj.PnP.IIuTrhuF5yFvPUfTAFrIgPBQZTV2jY3Jiq', 'ADMIN');

-- Contraseña para developerUser: dev456
INSERT INTO user (user_name, password, role) VALUES
('developerUser', '$2a$12$q1TCLY2y0aTuFTiJlLY/BOtfBbVr8ZqjOCcQ62CnvRTtQ8RcHLA2G', 'DEVELOPER');

-- Contraseña para managerUser: manager456
INSERT INTO user (user_name, password, role) VALUES
('managerUser', '$2a$12$dRymZEx.oeXvGqBBM4hGH.uyAWadU.tldO1fTZ2SDSzHZqNI2eQrK', 'MANAGER');

-- Contraseña para normalUser: user456
INSERT INTO user (user_name, password, role) VALUES
('normalUser', '$2a$12$8ogk2bsrWSvr7NDyQjRxcu12ItTdfv84ZASdnjWII4me2s8JYmnj6', 'USER');
```

---

## 🚀 Uso

### Autenticación

- **POST /auth/loginjwt**: Recibe JSON con username y password y devuelve un token JWT
- **POST /auth/registerjwt**: Registro de usuarios con roles

### Ejemplos de uso con token

Agregar en los headers de tus peticiones protegidas:

```
Authorization: Bearer <tu_token_jwt>
```

Endpoints protegidos con roles, por ejemplo:

```java
@PreAuthorize("hasRole('DEVELOPER')")
@GetMapping("/developer")
public ResponseEntity<String> developer() {
    return ResponseEntity.ok("Hola Developer, entraste con tu token JWT");
}
```

---

## 🐳 Dockerización

El proyecto incluye un archivo `docker-compose.yml` que levanta:

- Un contenedor con **MySQL**
- Un contenedor con **phpMyAdmin** para administrar la base
- Un contenedor con la aplicación **Spring Boot**

```bash
docker compose up --build -d
```

---

## 🛠️ Tecnologías usadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **JWT (jjwt)**
- **MySQL 8**
- **Docker & Docker Compose**
- **phpMyAdmin**

---

## 🔒 Consideraciones finales

- Las contraseñas están **encriptadas con BCrypt** para seguridad
- El sistema de **roles controla el acceso** a rutas críticas
- Se recomienda usar **HTTPS para producción**
- Puedes extender la seguridad agregando **OAuth2** o autorización más fina si es necesario

---

## 📧 Contacto

**Proyecto realizado por Chilaqui.**
