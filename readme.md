# 📦 EnviosExpress S.A.C. - Sistema de Rastreo de Paquetes (SOAP API)

Este proyecto implementa un sistema de seguimiento de paquetes a través de servicios web SOAP, utilizando Spring Boot, Apache CXF, Jakarta EE y PostgreSQL. Está diseñado con una arquitectura modular para registrar, consultar y rastrear paquetes, generando además logs detallados de cada consulta realizada al servicio de tracking. **EnviosExpress S.A.C.**

## 🚀 Tecnologías utilizadas

- Java 17+
- Spring Boot 3.x
- Apache CXF
- Jakarta EE (JPA, JAX-WS)
- JAXB (serialización XML)
- PostgreSQL
- Lombok

---

## 🧾 Objetivos de esta actividad

* 📤 Registrar paquetes con detalles como origen, destino, estado y eventos de seguimiento.
* 🔎 Consultar el estado actual de un paquete mediante su número de tracking.
* 🗃️ Generar un log detallado de cada consulta con la respuesta y eventos retornados.


---
## 🧩 Estructura de la actividad

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
├── repository/                 # Interfaces de acceso a datos con JPA
│   ├── PaqueteRepository.java
│   └── TrackingRequestRepository.java
├── services/                   # Interfaces de servicios web
│   ├── PaqueteService.java
│   └── TrackingRequestService.java
├── services/impl/              # Implementaciones de los servicios SOAP
│   ├── PaqueteServiceImpl.java
│   ├── TrackingRequestServiceImpl.java
│   └── TrackingRequest2Impl.java
└── SoapApplication.java        # Punto de entrada Spring Boot

```
---
## 📘 Descripción de entidades principales

* `Paquete`: Entidad principal del sistema. Contiene información del envío, ubicación actual y estado.
* `TrackingEvent`: Registro histórico de eventos por los que ha pasado un paquete.
* `GetTrackingStatusRequest`: Clase que registra solicitudes SOAP de rastreo.
* `GetTrackingStatusResponse`: Estructura XML devuelta al cliente con el estado actual y eventos.

## 🌐 Operaciones  SOAP disponibles
### 1. 📦 Servicio de Gestión de Paquetes
Servicio: `TrackingService`  
* WSDL: Disponible en http://localhost:8080/services/TrackingService?wsdl
* Operaciones:
```
| Método SOAP              | Descripción                                         |
|--------------------------|-----------------------------------------------------|
| `listAllPackages()`      | Lista todos los paquetes registrados                |
| `getPackageById(id)`     | Obtiene un paquete por su ID                        |
| `createPackage(paquete)` | Crea un nuevo paquete con su historial de eventos   |
| `deletePackage(id)`      | Elimina un paquete por ID                           |
| `getTrackingStatus(request)` | Consulta el estado actual de un paquete         |

```
### Servicios iniciados TrackingService
```
<wsdl:definitions xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:tns="http://impl.services.C.A.EnviosExpress_S/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns2="http://schemas.xmlsoap.org/soap/http" xmlns:ns1="http://services.C.A.EnviosExpress_S/" name="TrackingService" targetNamespace="http://impl.services.C.A.EnviosExpress_S/">
<wsdl:import location="http://localhost:8080/services/TrackingService?wsdl=TrackingService.wsdl" namespace="http://services.C.A.EnviosExpress_S/"> </wsdl:import>
<wsdl:binding name="TrackingServiceSoapBinding" type="ns1:TrackingService">
<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
<wsdl:operation name="getPackageById">
<soap:operation soapAction="" style="document"/>
<wsdl:input name="getPackageById">
<soap:body use="literal"/>
</wsdl:input>
<wsdl:output name="getPackageByIdResponse">
<soap:body use="literal"/>
</wsdl:output>
</wsdl:operation>
<wsdl:operation name="deletePackage">
<soap:operation soapAction="" style="document"/>
<wsdl:input name="deletePackage">
<soap:body use="literal"/>
</wsdl:input>
<wsdl:output name="deletePackageResponse">
<soap:body use="literal"/>
</wsdl:output>
</wsdl:operation>
<wsdl:operation name="listAllPackages">
<soap:operation soapAction="" style="document"/>
<wsdl:input name="listAllPackages">
<soap:body use="literal"/>
</wsdl:input>
<wsdl:output name="listAllPackagesResponse">
<soap:body use="literal"/>
</wsdl:output>
</wsdl:operation>
<wsdl:operation name="createPackage">
<soap:operation soapAction="" style="document"/>
<wsdl:input name="createPackage">
<soap:body use="literal"/>
</wsdl:input>
<wsdl:output name="createPackageResponse">
<soap:body use="literal"/>
</wsdl:output>
</wsdl:operation>
</wsdl:binding>
<wsdl:service name="TrackingService">
<wsdl:port binding="tns:TrackingServiceSoapBinding" name="PaqueteServiceImplPort">
<soap:address location="http://localhost:8080/services/TrackingService"/>
</wsdl:port>
</wsdl:service>
</wsdl:definitions>

```


### 2. 🔍 Servicio de Consulta de Tracking
* WSDL: http://localhost:8080/services/TrackingStatusService?wsdl
* Operación:
getTrackingStatus(GetTrackingStatusRequest request) - Retorna el estado del paquete y eventos asociados. También guarda automáticamente un log de consulta.

### Servicios iniciados TrackingStatusRequest
```
<wsdl:definitions xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:tns="http://impl.services.C.A.EnviosExpress_S/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns3="http://schemas.xmlsoap.org/soap/http" xmlns:ns1="http://services.C.A.EnviosExpress_S/" name="TrackingStatusService" targetNamespace="http://impl.services.C.A.EnviosExpress_S/">
<wsdl:import location="http://localhost:8080/services/TrackingStatusService?wsdl=TrackingPort.wsdl" namespace="http://services.C.A.EnviosExpress_S/"> </wsdl:import>
<wsdl:binding name="TrackingStatusServiceSoapBinding" type="ns1:TrackingPort">
<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
<wsdl:operation name="GetTrackingStatus">
<soap:operation soapAction="" style="document"/>
<wsdl:input name="GetTrackingStatus">
<soap:body use="literal"/>
</wsdl:input>
<wsdl:output name="GetTrackingStatusResponse">
<soap:body use="literal"/>
</wsdl:output>
<wsdl:fault name="TrackingError">
<soap:fault name="TrackingError" use="literal"/>
</wsdl:fault>
</wsdl:operation>
</wsdl:binding>
<wsdl:service name="TrackingStatusService">
<wsdl:port binding="tns:TrackingStatusServiceSoapBinding" name="TrackingRequestServiceImplPort">
<soap:address location="http://localhost:8080/services/TrackingStatusService"/>
</wsdl:port>
</wsdl:service>
</wsdl:definitions>
```
### ✉️ Ejemplo de Solicitud SOAP
```
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
<soap:Body>
<GetTrackingStatusRequest>
<trackingNumber>EX123456789</trackingNumber>
</GetTrackingStatusRequest>
</soap:Body>
</soap:Envelope>
```
### 📨 Respuesta
```
<GetTrackingStatusResponse>
  <status>En tránsito</status>
  <currentLocation>Ambato</currentLocation>
  <estimatedDeliveryDate>2025-05-31</estimatedDeliveryDate>
  <history>
    <event>
      <date>2025-05-26</date>
      <location>Quito</location>
      <description>Recolectado</description>
    </event>
  </history>
</GetTrackingStatusResponse>
```
### ❌ Ejemplo de Error SOAP
```
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <soap:Fault>
      <faultcode>soap:Client</faultcode>
      <faultstring>Paquete no encontrado para el tracking: EX000000000</faultstring>
    </soap:Fault>
  </soap:Body>
</soap:Envelope>

```


## ⚙️ Configuración (`application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/DB_EnviosExpress1
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update

cxf.path=/ws
```

---

## 🛠 Cómo ejecutar

1. Clonar el repositorio
2. Configurar la base de datos PostgreSQL (crear `envios_db`)
3. Modificar credenciales en `application.properties`
4. Ejecutar la aplicación:

```bash

mvn clean install
mvn spring-boot:run
```
---

## 👨‍💻 Autor

**Roberto Toapanta**  
**Javier Curimilma**  
Ingenieria en Tecnologías de la Información  
Proyecto académico - Universidad de las Fuerzas Armadas ESPE

---

## 📄 Licencia

Uso académico / demostrativo. No apto para producción sin revisión de seguridad.
