package EnviosExpress_S.A.C.model;

import EnviosExpress_S.A.C.adapter.LocalDateAdapter;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"paquete", "trackingLog"}) // Evita recursividad al imprimir objetos relacionados
@Entity
@Table(name = "tracking_events") // Mapea esta clase a la tabla tracking_events en la base de datos
@XmlRootElement(name = "TrackingEvent") // Nombre del elemento raíz XML
@XmlAccessorType(XmlAccessType.FIELD) // JAXB accede directamente a los atributos
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient // Este campo no se muestra en el XML, ya que es solo para la base de datos
    private Long id;

    @XmlElement(name = "date") // Muestra el campo "date" en el XML
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Adaptador para convertir LocalDate a String en formato yyyy-MM-dd
    private LocalDate date;

    @XmlElement(name = "location") // Ubicación donde ocurrió el evento
    private String location;

    @XmlElement(name = "description") // Descripción del evento (Ej. "Recolectado en agencia")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paquete_id", nullable = false) // Clave foránea hacia la tabla "paquetes"
    @XmlTransient // No queremos mostrar todo el paquete dentro de cada evento en el XML
    private Paquete paquete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracking_log_id") // Clave foránea hacia el log de tracking
    @XmlTransient
    private GetTrackingStatusRequest trackingLog;

    // Setter para establecer el paquete desde la lógica de negocio
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
