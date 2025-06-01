package EnviosExpress_S.A.C.model;

import EnviosExpress_S.A.C.adapter.LocalDateAdapter;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paquetes")
// Usamos @Entity y @Table para mapear esta clase a una tabla en la base de datos llamada "paquetes"
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Paquete") // Define el nombre raíz en la estructura XML
@XmlAccessorType(XmlAccessType.FIELD)
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient // No se muestra este campo en el XML ya que es un ID interno
    private Long id;

    @Column(name = "tracking_number", unique = true, nullable = false, length = 20)
    @XmlElement(name = "trackingNumber", required = true) // El campo trackingNumber se mostrará en el XML
    private String trackingNumber;

    @Column(name = "sender_name", nullable = false, length = 100)
    @XmlElement(name = "senderName") // Nombre del remitente
    private String senderName;

    @Column(name = "receiver_name", nullable = false, length = 100)
    @XmlElement(name = "receiverName") // Nombre del destinatario
    private String receiverName;

    @Column(name = "origin", nullable = false, length = 50)
    @XmlElement(name = "origin") // Ciudad de origen del paquete
    private String origin;

    @Column(name = "destination", nullable = false, length = 50)
    @XmlElement(name = "destination") // Ciudad de destino del paquete
    private String destination;

    @Column(name = "weight", nullable = false)
    @XmlElement(name = "weight") // Peso en kilogramos
    private double weight;

    @Column(name = "dimensions", length = 30)
    @XmlElement(name = "dimensions")  // Dimensiones del paquete
    private String dimensions;

    @Column(name = "status", length = 50)
    @XmlElement(name = "status") // Ubicación actual
    private String status;

    @Column(name = "current_location", length = 100)
    @XmlElement(name = "currentLocation") // Ubicación actual
    private String currentLocation;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "estimated_delivery_date")
    @XmlElement(name = "estimatedDeliveryDate") // Fecha estimada de entrega
    private LocalDate estimatedDeliveryDate;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "history") // Agrupamos eventos en <history>
    @XmlElement(name = "event") // Cada evento se muestra como <event>
    private List<TrackingEvent> history;


    // Métodos Getters para recuperar el estado, ubicación, fecha estimada y eventos de historial

    public String getStatus() {
        return status;
    }


    public String getCurrentLocation() {
        return currentLocation;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }


    public List<TrackingEvent> getHistory() {
        return history;
    }

    public void setHistory(List<TrackingEvent> history) {
        this.history = history;
    }
}
