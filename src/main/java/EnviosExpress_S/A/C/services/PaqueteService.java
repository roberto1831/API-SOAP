package EnviosExpress_S.A.C.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import EnviosExpress_S.A.C.model.Paquete;
import java.util.List;

@WebService(name = "TrackingService") // Esta interfaz se expone como servicio SOAP con el nombre "TrackingService"
public interface PaqueteService {

    @WebMethod(operationName = "listAllPackages") // Define la operación SOAP para listar paquetes
    List<Paquete> listPaquetes();

    @WebMethod(operationName = "getPackageById") // Definimos la operación SOAP para obtener un paquete por ID
    @WebResult(name = "packageId") // Nombre del resultado en el XML SOAP
    Paquete getPaquete(@WebParam(name = "packageId") Long id); // Parámetro de entrada para la consulta

    @WebMethod(operationName = "createPackage") // Definimos la operación SOAP para crear un paquete
    Paquete createPaquete(@WebParam(name = "package") Paquete paquete); // El paquete a crear se pasa como parámetro

    @WebMethod(operationName = "deletePackage") // Definimos la operación SOAP para eliminar un paquete
    boolean deletePaquete(@WebParam(name = "packageId") Long id); // Recibe el ID del paquete a eliminar
}
