package EnviosExpress_S.A.C.services;

import EnviosExpress_S.A.C.exception.TrackingError;
import EnviosExpress_S.A.C.model.GetTrackingStatusRequest;
import EnviosExpress_S.A.C.model.GetTrackingStatusResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@WebService(name = "TrackingPort") // Definimos el nombre del puerto SOAP en el WSDL
@XmlSeeAlso({
        EnviosExpress_S.A.C.model.GetTrackingStatusRequest.class,
        EnviosExpress_S.A.C.model.GetTrackingStatusResponse.class,
        EnviosExpress_S.A.C.model.Paquete.class,
        EnviosExpress_S.A.C.model.TrackingEvent.class
}) // Aseguramos que estas clases estén disponibles
public interface TrackingRequestService {

    @WebMethod(operationName = "GetTrackingStatus") // Nombre visible en el WSDL para esta operación
    @WebResult(name = "GetTrackingStatusResponse") // Nombre de la respuesta en el XML SOAP
    GetTrackingStatusResponse getTrackingStatus(
            @WebParam(name = "GetTrackingStatusRequest") GetTrackingStatusRequest request // Nombre de la petición en el XML SOAP
    ) throws TrackingError; // Esta operación puede lanzar una excepción  en caso de error
}
