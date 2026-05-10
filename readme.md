# 📦 EnviosExpress S.A.C. – Sistema de Rastreo de Paquetes (SOAP API)

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Apache CXF](https://img.shields.io/badge/Apache_CXF-SOAP-D22128?style=for-the-badge&logo=apache&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta_EE-JPA%20%7C%20JAX--WS-FF6C37?style=for-the-badge)

> API SOAP para registro, consulta y rastreo de paquetes en tiempo real, con logging automático de cada consulta al servicio de tracking.

---

## 🎯 Funcionalidades

| Función | Descripción |
|---|---|
| 📤 Registro de paquetes | Origen, destino, estado y eventos de seguimiento |
| 🔎 Consulta de estado | Por número de tracking en tiempo real |
| 🗃️ Log de consultas | Registro automático de cada solicitud SOAP |

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17+ | Lenguaje principal |
| Spring Boot | 3.x | Framework base |
| Apache CXF | Latest | Publicación de servicios SOAP |
| Jakarta EE | JPA + JAX-WS | Persistencia y servicios web |
| JAXB | Latest | Serialización XML |
| PostgreSQL | Latest | Base de datos relacional |
| Lombok | Latest | Reducción de boilerplate |

---

## 📂 Estructura del Proyecto

```
EnviosExpress_S.A.C/
├── adapter/                    # Adaptadores de fecha para JAXB
│   ├── LocalDateAdapter.java
│   └── LocalDateTimeAdapter.java
├── config/                     # Configuración CXF para endpoints SOAP
│   └── CxfConfig.java
├── exception/                  # Excepciones personalizadas para fallos SOAP
│   └── TrackingError.java
├── model/                      # Entidades JPA y modelos JAXB
│   ├── GetTrackingStatusRequest.java
│   ├── GetTrackingStatusResponse.java
│   ├── Paquete.java
│   └── TrackingEvent.java
├── repository/                 # Interfaces JPA
│   ├── PaqueteRepository.java
│   └── TrackingRequestRepository.java
├── services/                   # Interfaces de servicios web
│   ├── PaqueteService.java
│   └── TrackingRequestService.java
├── services/impl/              # Implementaciones SOAP
│   ├── PaqueteServiceImpl.java
│   ├── TrackingRequestServiceImpl.java
│   └── TrackingRequest2Impl.java
└── SoapApplication.java        # Punto de entrada Spring Boot
```

---

## 🌐 Servicios SOAP Disponibles

### 1. 📦 TrackingService
**WSDL:** `http://localhost:8080/services/TrackingService?wsdl`

| Operación | Descripción |
|---|---|
| `listAllPackages()` | Lista todos los paquetes registrados |
| `getPackageById(id)` | Obtiene un paquete por su ID |
| `createPackage(paquete)` | Crea un nuevo paquete con historial de eventos |
| `deletePackage(id)` | Elimina un paquete por ID |
| `getTrackingStatus(request)` | Consulta el estado actual de un paquete |

### 2. 🔍 TrackingStatusService
**WSDL:** `http://localhost:8080/services/TrackingStatusService?wsdl`

Operación `GetTrackingStatus` — retorna estado del paquete, eventos asociados y guarda automáticamente un log de consulta.

---

## 📨 Ejemplos SOAP

### ✉️ Solicitud de Tracking
```xml

   
   
      
         
            EXP987694322
         
      
   

```

### ✅ Respuesta Exitosa
```xml

   
      
         Despachado
         Machala
         2025-06-03
         
            
               2025-05-26
               Cuenca
               Recolectado en agencia principal
            
            
               2025-05-27
               Riobamba
               En tránsito hacia Ambato
            
         
      
   

```

### ❌ Respuesta de Error
```xml

   soap:Server
   Paquete no encontrado: EXP987694389
   
      
         trackingNumber
         404
      
   

```

---

## ⚙️ Configuración (`application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/DB_EnviosExpress1
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD_AQUI
spring.jpa.hibernate.ddl-auto=update
cxf.path=/ws
```

> ⚠️ **Nunca subas credenciales reales al repositorio.** Usa variables de entorno o un archivo `.env` ignorado por `.gitignore`.

---

## 🚀 Cómo Ejecutar

### Prerrequisitos
- Java 17+
- Maven
- PostgreSQL corriendo localmente

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/roberto1831/API-SOAP.git
cd API-SOAP

# 2. Crear la base de datos en PostgreSQL
createdb DB_EnviosExpress1

# 3. Configurar credenciales en application.properties

# 4. Compilar y ejecutar
mvn clean install
mvn spring-boot:run
```

---

## 👨‍💻 Autores

**Ing.Roberto Toapanta** · [GitHub](https://github.com/roberto1831) · [LinkedIn](https://linkedin.com/in/roberto1831)  

Ingeniería en Tecnologías de la Información – Universidad de las Fuerzas Armadas ESPE

---

## 📄 Licencia

Uso académico / demostrativo. No apto para producción sin revisión de seguridad.
