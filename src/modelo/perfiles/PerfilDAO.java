package modelo.perfiles;

import modelo.conexiones.Conexion;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO {
    private Conexion conexion;

    public PerfilDAO() {
        // Obtener la instancia única de Conexion
        this.conexion = Conexion.getInstancia();
    }

    // Crear un perfil en la base de datos
    public void crearPerfil(Perfil perf) {
        BasicDBObject documento = new BasicDBObject();
        documento.put("NOMBRE", perf.getNombre());
        documento.put("POSICION", perf.getPosicion());
        documento.put("ALTURA", perf.getAltura());
        documento.put("PESO", perf.getPeso());
        documento.put("EDAD", perf.getEdad());

        // Usar la colección de perfiles
        conexion.getColeccionPerfiles().insert(documento);
    }

    // Obtener todos los perfiles de la base de datos
    public List<Perfil> obtenerPerfiles() {
        List<Perfil> perfiles = new ArrayList<>();
        DBCursor cursor = conexion.getColeccionPerfiles().find(); // Acceder a la colección de perfiles
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            String nombre = (String) doc.get("NOMBRE");
            String posicion = (String) doc.get("POSICION");
            Float altura = ((Double) doc.get("ALTURA")).floatValue();
            Float peso = ((Double) doc.get("PESO")).floatValue();
            int edad = ((Number) doc.get("EDAD")).intValue();

            Perfil perf = new Perfil(nombre, posicion, altura, peso, edad);
            perfiles.add(perf);
        }
        return perfiles;
    }

    // Actualizar un perfil en la base de datos
    public boolean actualizarPerfil(Perfil perfil) {
        BasicDBObject query = new BasicDBObject();
        query.put("NOMBRE", perfil.getNombre());

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("POSICION", perfil.getPosicion());
        newDocument.put("ALTURA", perfil.getAltura());
        newDocument.put("PESO", perfil.getPeso());
        newDocument.put("EDAD", perfil.getEdad());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        conexion.getColeccionPerfiles().update(query, updateObject); // Acceder a la colección de perfiles
        return true;
    }

    // Obtener jugadores por posición
    public List<Perfil> obtenerJugadoresPorPosicion(String posicion) {
        List<Perfil> jugadores = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        query.put("POSICION", posicion);

        DBCursor cursor = conexion.getColeccionPerfiles().find(query); // Acceder a la colección de perfiles
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            String nombre = (String) doc.get("NOMBRE");
            String pos = (String) doc.get("POSICION");
            Float altura = ((Double) doc.get("ALTURA")).floatValue();
            Float peso = ((Double) doc.get("PESO")).floatValue();
            int edad = ((Number) doc.get("EDAD")).intValue();

            Perfil jugador = new Perfil(nombre, pos, altura, peso, edad);
            jugadores.add(jugador);
        }
        return jugadores;
    }
}