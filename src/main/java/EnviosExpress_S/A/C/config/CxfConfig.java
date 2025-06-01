package EnviosExpress_S.A.C.config;

import EnviosExpress_S.A.C.services.PaqueteService;
import EnviosExpress_S.A.C.services.TrackingRequestService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Clase de configuración de Apache CXF para exponer los servicios SOAP como endpoints.
 * Utilizamos anotaciones de Spring para que esta clase sea detectada automáticamente como configuración.
 */
@Configuration
public class CxfConfig {

    /**
     * Bean que configura el endpoint SOAP para el servicio de gestión de paquetes (PaqueteService).
     * Este endpoint se publicará en: http://localhost:8080/services/TrackingService?wsdl
     */
    @Bean
    public Endpoint paqueteEndpoint(Bus bus, PaqueteService paqueteService) {

        EndpointImpl endpoint = new EndpointImpl(bus, paqueteService); // Se vincula el bus de CXF y el servicio

        endpoint.publish("TrackingService"); // Define la URL relativa donde se expone el WSDL
        return endpoint;
    }

    /**
     * Bean que configura el endpoint SOAP para el servicio de consulta de tracking (TrackingRequestService).
     * Este endpoint se publicará en: http://localhost:8080/services/TrackingStatusService?wsdl
     */
    @Bean
    public Endpoint trackingStatusEndpoint(Bus bus, TrackingRequestService trackingRequestService) {
        EndpointImpl endpoint = new EndpointImpl(bus, trackingRequestService);
        endpoint.publish("/TrackingStatusService");
        return endpoint;
    }

}