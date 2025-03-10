package modelo.estadisticas;

import modelo.estadisticas.EstadisticasJugador;
import modelo.Observer.Sujeto;
import modelo.conexiones.Conexion;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.List;

public class EstadisticasDAO extends Sujeto { // Hereda de Sujeto
    private Conexion conexion;

    public EstadisticasDAO() {
        // Obtener la instancia única de Conexion
        this.conexion = Conexion.getInstancia();
    }

    // Crear estadísticas para un jugador
    public void crearEstadisticas(String nombreJugador, EstadisticasJugador estadisticas) {
        BasicDBObject query = new BasicDBObject();
        query.put("NOMBRE", nombreJugador);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("REBOTES", estadisticas.getRebotes());
        newDocument.put("BLOQUEOS", estadisticas.getBloqueos());
        newDocument.put("ASISTENCIAS", estadisticas.getAsistencias());
        newDocument.put("PUNTOS", estadisticas.getPuntos());
        newDocument.put("ROBOS", estadisticas.getRobos());
        newDocument.put("PARTIDOS_JUGADOS", estadisticas.getPartidosJugados());
        newDocument.put("MVPS", estadisticas.getMvps());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        // Actualizar la colección de perfiles
        conexion.getColeccionPerfiles().update(query, updateObject);

        // Notificar a los observadores que se han actualizado las estadísticas
        notificarObservadores("Estadísticas actualizadas para el jugador: " + nombreJugador);
    }

    // Obtener estadísticas de un jugador
    public EstadisticasJugador obtenerEstadisticas(String nombreJugador) {
        BasicDBObject query = new BasicDBObject();
        query.put("NOMBRE", nombreJugador);

        DBObject doc = conexion.getColeccionPerfiles().findOne(query);
        if (doc != null) {
            int rebotes = doc.get("REBOTES") != null ? ((Number) doc.get("REBOTES")).intValue() : 0;
            int bloqueos = doc.get("BLOQUEOS") != null ? ((Number) doc.get("BLOQUEOS")).intValue() : 0;
            int asistencias = doc.get("ASISTENCIAS") != null ? ((Number) doc.get("ASISTENCIAS")).intValue() : 0;
            int puntos = doc.get("PUNTOS") != null ? ((Number) doc.get("PUNTOS")).intValue() : 0;
            int robos = doc.get("ROBOS") != null ? ((Number) doc.get("ROBOS")).intValue() : 0;
            int partidosJugados = doc.get("PARTIDOS_JUGADOS") != null ? ((Number) doc.get("PARTIDOS_JUGADOS")).intValue() : 0;
            int mvps = doc.get("MVPS") != null ? ((Number) doc.get("MVPS")).intValue() : 0;

            return new EstadisticasJugador(rebotes, bloqueos, asistencias, puntos, robos, partidosJugados, mvps);
        }
        return null;
    }
}