package EnviosExpress_S.A.C.model;

import EnviosExpress_S.A.C.adapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data // Genera automáticamente getters, setters, toString, equals y hashCode

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "GetTrackingStatusResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetTrackingStatusResponse extends GetTrackingStatusRequest {
    // Esta clase hereda de GetTrackingStatusRequest para aprovechar campos reutilizables
    // aunque normalmente solo se devuelven los datos de estado y eventos

    @XmlElement(name = "status") // Estado actual del paquete (ej: "En tránsito")
    private String status;

    @XmlElement(name = "currentLocation") // Ubicación actual del paquete
    private String currentLocation;

    @XmlElement(name = "estimatedDeliveryDate") // Fecha estimada de entrega del paquete
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Se usa adaptador para que el formato sea yyyy-MM-dd
    private LocalDate estimatedDeliveryDate;

    @XmlElementWrapper(name = "history") // Crea una etiqueta <history> que agrupa los eventos
    @XmlElement(name = "event") // Cada evento en la lista se serializa como <event>
    private List<TrackingEvent> history;

    // Setters personalizados necesarios para JAXB
    public void setStatus(String status) {
        this.status = status;
    }



    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }



    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }


    public void setHistory(List<TrackingEvent> history) {
        this.history = history;
    }
}
