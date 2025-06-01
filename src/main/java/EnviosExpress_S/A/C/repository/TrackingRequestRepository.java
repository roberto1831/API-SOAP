package EnviosExpress_S.A.C.repository;

import EnviosExpress_S.A.C.model.GetTrackingStatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Esta anotación le dice a Spring que esta interfaz es un componente de acceso a datos
public interface TrackingRequestRepository extends JpaRepository<GetTrackingStatusRequest, Long> {


}