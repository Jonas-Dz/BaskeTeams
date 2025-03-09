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
            int rebotes = ((Number) doc.get("REBOTES")).intValue();
            int bloqueos = ((Number) doc.get("BLOQUEOS")).intValue();
            int asistencias = ((Number) doc.get("ASISTENCIAS")).intValue();
            int puntos = ((Number) doc.get("PUNTOS")).intValue();
            int robos = ((Number) doc.get("ROBOS")).intValue();
            int partidosJugados = ((Number) doc.get("PARTIDOS_JUGADOS")).intValue();
            int mvps = ((Number) doc.get("MVPS")).intValue();

            return new EstadisticasJugador(rebotes, bloqueos, asistencias, puntos, robos, partidosJugados, mvps);
        }
        return null;
    }
}