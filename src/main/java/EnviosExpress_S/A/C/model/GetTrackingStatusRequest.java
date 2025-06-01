package EnviosExpress_S.A.C.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Esta clase representa la entidad que guarda un registro de cada solicitud realizada al servicio de tracking.
 * Es útil para llevar un historial de todas las búsquedas hechas por número de seguimiento.
 */

@Entity
@Table(name = "GetTrackingStatusRequest")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"returnedTrackingEvents"})
@XmlRootElement(name = "GetTrackingStatusRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetTrackingStatusRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @XmlElement(name = "trackingNumber", required = true)
    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;
    // Timestamps para saber cuándo se hizo la solicitud
    @Column(name = "request_timestamp")
    @XmlTransient
    private LocalDate requestTimestamp;
    // Datos de respuesta guardados por si se quiere auditar o consultar después
    @Column(name = "response_status")
    @XmlTransient
    private String responseStatus;

    @Column(name = "response_location")
    @XmlTransient
    private String responseLocation;

    @Column(name = "response_estimated_delivery_date")
    @XmlTransient
    private LocalDate responseEstimatedDeliveryDate;

    @Column(name = "response_timestamp")
    @XmlTransient
    private LocalDate responseTimestamp;

    @OneToMany(mappedBy = "trackingLog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlTransient // No es necesario que los eventos se devuelvan aquí en el XML
    private List<TrackingEvent> returnedTrackingEvents;

    // Getter y Setter necesarios para JAXB y JPA
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    public void setRequestTimestamp(LocalDate requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }


    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }


    public void setResponseLocation(String responseLocation) {
        this.responseLocation = responseLocation;
    }


    public void setResponseEstimatedDeliveryDate(LocalDate responseEstimatedDeliveryDate) {
        this.responseEstimatedDeliveryDate = responseEstimatedDeliveryDate;
    }


    public void setResponseTimestamp(LocalDate responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }


}
