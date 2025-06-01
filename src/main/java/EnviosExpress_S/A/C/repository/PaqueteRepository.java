package EnviosExpress_S.A.C.repository;

import EnviosExpress_S.A.C.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Anotación para indicar que esta interfaz es un componente de acceso a datos
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

    // Método personalizado que busca un paquete por su número de tracking (clave única)
    Optional<Paquete> findByTrackingNumber(String trackingNumber);

}
