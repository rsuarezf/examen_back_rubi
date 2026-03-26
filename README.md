# Proyecto: examen_back_Rubi_Suarez

## 1. Descripción

Este proyecto es un sistema fullstack desarrollado con Angular 18 en el frontend y Spring Boot 3 en el backend. Utiliza PostgreSQL 18 como base de datos y Java 17.0.2 para la lógica del servidor.

El sistema permite obtener los usuarios y sus domicilios, incluyendo:

* Cálculo de la edad del usuario a partir de su fecha de nacimiento
* Exposición de los datos mediante API REST en formato JSON

## 2. Tecnologías utilizadas

* Frontend: Angular 18
* Backend: Spring Boot 3
* Lenguaje: Java 17.0.2
* Base de datos: PostgreSQL 18
* Build tool: Maven (backend), Angular CLI (frontend)
* Dependencias principales (backend): Spring Web, Spring Data JPA, PostgreSQL Driver

## 3. Requisitos del sistema

* Java JDK 17.0.2
* Node.js ≥ 20
* Angular CLI ≥ 18
* PostgreSQL 15
* Maven ≥ 3.x

## 4. Configuración del backend


### 4.1 Configuración de base de datos

1. Editar `src/main/resources/application.properties`:
Modificar los datos de username y password

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/back
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
2. Ejecutar el archivo 'src/main/resources/db/migracion/archivo V1_init.sql' en postgres para crear la base de datos que se ocupará para el proyeto.
   ## Contenido del archivo a ejecutar
CREATE DATABASE back
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

## 5. Ejecución del proyecto

El backend estará disponible en: [http://localhost:8081/examen_back_rubi/]

## 5. Configuración del frontend (Angular 18)

1. Clonar frontend dentro del proyecto o en carpeta separada:

```bash
git clone https://github.com/rsuarezf/examen_front_rubi
cd examen_front_Rubi_Suarez
```
2. Configurar el puerto de la URL del backend en `app/services/usuarios.ts`:

```typescript
 apiUrl = 'http://localhost:8081/examen_back_rubi/api/usuarios';

```

4. Ejecutar frontend:

```bash
npm install
ng serve
```

Acceder desde: [http://localhost:4200/]
