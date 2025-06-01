package EnviosExpress_S.A.C.exception;

import jakarta.xml.ws.WebFault;


/**
 * Clase personalizada de excepción para representar errores en el servicio de tracking.
 * Se utiliza para devolver errores SOAP con información más detallada al cliente.
 */
@WebFault(name = "TrackingError", targetNamespace = "http://logistica.com/ws/tracking")
public class TrackingError extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode; // Código de error (por ejemplo: 404, 500)
    private String fieldWithError; // Campo donde ocurrió el error

    /**
     * Constructor completo que permite especificar el mensaje, el código de error, el detalle
     * y el campo relacionado con el error. Es útil para construir respuestas SOAP claras.
     */
    public TrackingError(String message, Integer errorCode, String detailedMessage, String fieldWithError) {
        super(message + " - Detalles: " + detailedMessage);
        this.errorCode = String.valueOf(errorCode);
        this.fieldWithError = fieldWithError;
    }

    /**
     * Constructor simple, útil cuando no se requiere información adicional.
     */
    public TrackingError(String message) {
        super(message);
        this.errorCode = "500"; // Por defecto, puedes poner 500 o dejarlo como null
        this.fieldWithError = "desconocido";
    }
    // Getters para exponer la información del error
    public String getErrorCode() {
        return errorCode;
    }

    public String getFieldWithError() {
        return fieldWithError;
    }
}
