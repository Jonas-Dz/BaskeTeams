package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

public class AlineacionDAO extends Sujeto { // Hereda de Sujeto
    private Conexion conexion;

    public AlineacionDAO() {
        // Obtener la instancia única de Conexion
        this.conexion = Conexion.getInstancia();
    }

    // Método para obtener los jugadores por posición
    public List<Perfil> obtenerJugadoresPorPosicion(String posicion) {
        List<Perfil> jugadores = new ArrayList<>();

        // Crear la consulta para filtrar por posición
        BasicDBObject query = new BasicDBObject();
        query.put("POSICION", posicion);

        // Realizar la consulta sobre la colección 'perfiles'
        DBCursor cursor = conexion.getColeccionPerfiles().find(query);
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            String nombre = (String) doc.get("NOMBRE");
            String pos = (String) doc.get("POSICION");
            Float altura = ((Double) doc.get("ALTURA")).floatValue();
            Float peso = ((Double) doc.get("PESO")).floatValue();
            int edad = ((Number) doc.get("EDAD")).intValue();

            // Crear el objeto Perfil y agregarlo a la lista
            Perfil jugador = new Perfil(nombre, pos, altura, peso, edad);
            jugadores.add(jugador);
        }

        // Notificar a los observadores que se han obtenido nuevos jugadores
        notificarObservadores("Se han obtenido jugadores para la posición: " + posicion);

        return jugadores; // Devuelve la lista de jugadores
    }
}