package EnviosExpress_S.A.C.services.impl;

import EnviosExpress_S.A.C.model.Paquete;
import EnviosExpress_S.A.C.model.TrackingEvent;
import EnviosExpress_S.A.C.repository.PaqueteRepository;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import EnviosExpress_S.A.C.services.PaqueteService;

import java.util.List;
import java.util.Optional;

@Component // Anotación de Spring para indicar que esta clase es un componente gestionado
@WebService(serviceName = "TrackingService") // Exposición como servicio SOAP con nombre personalizado
public class PaqueteServiceImpl implements PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository; // Inyección del repositorio para manejar persistencia

    // Método que devuelve todos los paquetes registrados
    @Override
    public List<Paquete> listPaquetes() {
        return paqueteRepository.findAll();
    }

    // Método para obtener un paquete específico por su ID
    @Override
    public Paquete getPaquete(Long id) {
        return paqueteRepository.findById(id).orElse(null);
    }

    // Método para crear un nuevo paquete
    @Override
    public Paquete createPaquete(Paquete paquete) {
        // Asocia cada evento con su paquete antes de guardarlo (clave foránea)
        if (paquete.getHistory() != null && !paquete.getHistory().isEmpty()) {
            for (TrackingEvent event : paquete.getHistory()) {
                event.setPaquete(paquete);
            }
        }

        // Guarda el paquete (junto con sus eventos si existen)
        return paqueteRepository.save(paquete);
    }

    // Método para eliminar un paquete por su ID
    @Override
    public boolean deletePaquete(Long id) {
        Optional<Paquete> paquete = paqueteRepository.findById(id);
        if (paquete.isPresent()) {
            paqueteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
