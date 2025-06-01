package EnviosExpress_S.A.C.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter; // Paquete donde se ubica el adaptador
import java.time.LocalDate; // Importación necesaria para trabajar con fechas
import java.time.format.DateTimeFormatter;

/**
 * Clase adaptadora personalizada para convertir entre LocalDate y String
 * en el contexto de serialización/deserialización con JAXB (XML).
 */

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    // Definimos el formato que se usará para las fechas en el XML
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Método que transforma un String recibido desde el XML a un objeto LocalDate de Java.
     * Este método es invocado automáticamente por JAXB cuando se recibe una fecha como texto.
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        if (v == null || v.trim().isEmpty()) {
            return null; // Retorna null si el valor está vacío, para evitar errores
        }
        return LocalDate.parse(v, FORMATTER); // Convierte el texto al objeto LocalDate
    }
    /**
     * Método que transforma un LocalDate en un String para ser enviado en el XML.
     * JAXB invoca este método cuando debe serializar la fecha.
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        if (v == null) {
            return null; // Si no hay fecha, se envía null
        }
        return v.format(FORMATTER); // Formatea la fecha como yyyy-MM-dd para el XML
    }
}