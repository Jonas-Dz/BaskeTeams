package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

public class AlineacionDAO {
    private Conexion conexion;

    public AlineacionDAO() {
        conexion = new Conexion(); // Usamos la clase Conexion que ya tienes configurada
    }

    // Método para obtener los jugadores por posición
    public List<Perfil> obtenerJugadoresPorPosicion(String posicion) {
        List<Perfil> jugadores = new ArrayList<>();
        
        BasicDBObject query = new BasicDBObject();
        query.put("POSICION", posicion);  // Filtramos por posición

        // Realizamos la consulta sobre la colección 'perfiles'
        DBCursor cursor = conexion.coleccion.find(query);
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
        
        return jugadores; // Devuelve la lista de jugadores
    }
}


//Mejorable con singleton de la conexion y observer para la actualizcion