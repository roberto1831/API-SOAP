package EnviosExpress_S.A.C.services.impl;

import EnviosExpress_S.A.C.exception.TrackingError;
import EnviosExpress_S.A.C.model.GetTrackingStatusRequest;
import EnviosExpress_S.A.C.model.GetTrackingStatusResponse;
import EnviosExpress_S.A.C.model.Paquete;
import EnviosExpress_S.A.C.model.TrackingEvent;
import EnviosExpress_S.A.C.repository.PaqueteRepository;
import EnviosExpress_S.A.C.repository.TrackingRequestRepository;
import EnviosExpress_S.A.C.services.TrackingRequestService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component // Este componente es gestionado por Spring Boot
@WebService(serviceName = "TrackingStatusService", endpointInterface = "EnviosExpress_S.A.C.services.TrackingRequestService")
public class TrackingRequestServiceImpl implements TrackingRequestService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private TrackingRequestRepository trackingRequestRepository;

    @Override
    public GetTrackingStatusResponse getTrackingStatus(GetTrackingStatusRequest request) throws TrackingError {
        String trackingNumber = request.getTrackingNumber(); // Se obtiene el número de seguimiento enviado por el cliente

        // Busca el paquete en la base de datos usando el número de seguimiento
        Paquete paquete = paqueteRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new TrackingError(
                        "Error de tracking",           // Mensaje genérico
                        404,                          // Código de error tipo HTTP
                        "Paquete no encontrado para el tracking: " + trackingNumber, // Detalles
                        "trackingNumber"              // Campo que causó el error
                ));

        // Prepara la respuesta que se enviará al cliente SOAP
        GetTrackingStatusResponse response = new GetTrackingStatusResponse();
        response.setStatus(paquete.getStatus());
        response.setCurrentLocation(paquete.getCurrentLocation());
        response.setEstimatedDeliveryDate(paquete.getEstimatedDeliveryDate());
        response.setHistory(paquete.getHistory()); // Lista de eventos del paquete

        // Crea una nueva entrada de log con la información de esta consulta
        GetTrackingStatusRequest log = new GetTrackingStatusRequest();

        // También se imprime en consola el trackingNumber como prueba
        GetTrackingStatusRequest prueba = new GetTrackingStatusRequest();
        System.out.println(prueba.getTrackingNumber());

        // Se registran los datos de la consulta
        log.setTrackingNumber(trackingNumber);
        log.setRequestTimestamp(LocalDate.now());
        log.setResponseStatus(paquete.getStatus());
        log.setResponseLocation(paquete.getCurrentLocation());
        log.setResponseEstimatedDeliveryDate(paquete.getEstimatedDeliveryDate());
        log.setResponseTimestamp(LocalDate.now());

        // Se guarda el log de esta consulta en la base de datos
        trackingRequestRepository.save(log);

        // Finalmente se devuelve la respuesta al cliente
        return response;
    }
}
