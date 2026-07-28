# SGARRHH — Sistema de Gestión de Recursos Humanos

Aplicación web para la gestión de recursos humanos, desarrollada como trabajo final de la
**Especialización en Desarrollo de Sistemas para Ambiente Internet con Tecnología Orientada
a Objetos** — Facultad Politécnica, Universidad Nacional del Este (Paraguay), 2018.

> Proyecto académico de 2018. Se publica como referencia del trabajo de posgrado; no
> recibe mantenimiento activo.

## Stack

| Capa | Tecnología |
|---|---|
| Framework | Spring Boot 2.0 (MVC, Data JPA, Security) |
| Persistencia | Hibernate / JPA · PostgreSQL (soporte alternativo MySQL / MariaDB / H2) |
| Vistas | Thymeleaf · MaterializeCSS |
| Reportes | JasperReports · Apache POI · Flying Saucer (PDF) |
| Build | Maven (wrapper incluido) |

## Arquitectura

Organización por capas bajo `py.sgarrhh`:

```
controller/     endpoints MVC
service/        lógica de negocio
repository/     acceso a datos (Spring Data JPA)
models/         entidades JPA
security/       autenticación y autorización (Spring Security)
configuration/  configuración de la aplicación
util/           utilidades transversales
```

El modelo de datos está documentado en `src/main/java/diagram/DER.erm`.

## Ejecución local

La configuración de base de datos se toma de variables de entorno; no hay credenciales
en el repositorio.

```bash
export DB_URL="jdbc:postgresql://localhost:5432/rrhh_db"
export DB_USER="tu_usuario"
export DB_PASSWORD="tu_password"

./mvnw spring-boot:run
```

Disponible en `http://localhost:8080`.

`spring.jpa.hibernate.ddl-auto=none`: el esquema debe existir previamente.

## Licencia

Publicado con fines académicos y de referencia.
