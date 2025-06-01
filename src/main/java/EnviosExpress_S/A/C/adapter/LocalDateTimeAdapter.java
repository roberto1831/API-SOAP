package EnviosExpress_S.A.C.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador personalizado para JAXB que permite convertir entre
 * objetos LocalDateTime de Java y cadenas String en formato XML.
 */

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    // Formato ISO básico con fecha y hora (ej: 2025-05-31T14:30:00)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    /**
     * Método llamado automáticamente por JAXB cuando se recibe un valor de tipo String
     * desde el XML y se necesita convertirlo a LocalDateTime.
     */
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        // Validación básica para evitar errores de parseo si el string viene vacío o nulo
        return (v == null || v.isBlank()) ? null : LocalDateTime.parse(v, FORMATTER);
    }
    /**
     * Método llamado por JAXB cuando se debe enviar un LocalDateTime en el XML.
     * Convierte el objeto a un String en el formato especificado.
     */
    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return (v == null) ? null : v.format(FORMATTER);
    }
}