package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danip
 */
public class PerfilDAO {
    //CREAR PERFILES EN LA BASE DE DATOS
    public void crearPerfil(Perfil perf){
        Conexion objCon = new Conexion();
        BasicDBObject documento = new BasicDBObject();
        documento.put("NOMBRE", perf.getNombre());
        documento.put("POSICION", perf.getPosicion());
        documento.put("ALTURA", perf.getAltura());
        documento.put("PESO", perf.getPeso());
        documento.put("EDAD", perf.getEdad());
        documento.put("REBOTES", perf.getRebotes());
        documento.put("BLOQUEOS", perf.getBloqueos());
        documento.put("ASISTENCIAS", perf.getAsistencias());
        documento.put("PUNTOS", perf.getPuntos());
        documento.put("ROBOS", perf.getRobos());
        documento.put("PARTIDOS JUGADOS", perf.getPartjugados());
        documento.put("MVPS", perf.getMvps());
        objCon.coleccion.insert(documento);
    }
    
    //OBTENER LOS PERFILES DE LA BASE DE DATOS
    public List<Perfil> obtenerPerfiles() {
        List<Perfil> perfs = new ArrayList<>();
        Conexion objCon = new Conexion();
        DBCursor cursor = objCon.coleccion.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            String nombre = (String) doc.get("NOMBRE");
            String posicion = (String) doc.get("POSICION");
            Float altura = ((Double) doc.get("ALTURA")).floatValue();
            Float peso = ((Double) doc.get("PESO")).floatValue(); // Conversión explícita
            int edad = ((Number) doc.get("EDAD")).intValue();
            int rebotes = ((Number) doc.get("REBOTES")).intValue();
            int bloqueos = ((Number) doc.get("BLOQUEOS")).intValue();
            int asistencias = ((Number) doc.get("ASISTENCIAS")).intValue();
            int puntos = ((Number) doc.get("PUNTOS")).intValue();
            int robos = ((Number) doc.get("ROBOS")).intValue();
            int partjugados = ((Number) doc.get("PARTIDOS JUGADOS")).intValue();
            int mvps = ((Number) doc.get("MVPS")).intValue();

            Perfil perf= new Perfil(nombre, posicion, altura, peso, edad, rebotes, bloqueos, asistencias, puntos, robos, partjugados, mvps);
            perfs.add(perf);
        }
        return perfs;
    }
    
    //ACTUALIZAR PERFILES EN LA BASE DE DATOS
    public boolean actualizarPefiles(Perfil perfil){
        Conexion objCon = new Conexion();
        BasicDBObject query = new BasicDBObject();
        query.put("NOMBRE", perfil.getNombre());
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("POSICION", perfil.getPosicion());
        newDocument.put("ALTURA", perfil.getAltura());
        newDocument.put("PESO", perfil.getPeso());
        newDocument.put("EDAD", perfil.getEdad());
        newDocument.put("REBOTES", perfil.getRebotes());
        newDocument.put("BLOQUEOS", perfil.getBloqueos());
        newDocument.put("ASISTENCIAS", perfil.getAsistencias());
        newDocument.put("PUNTOS", perfil.getPuntos());
        newDocument.put("ROBOS", perfil.getRobos());
        newDocument.put("PARTIDOS JUGADOS", perfil.getPartjugados());
        newDocument.put("MVPS", perfil.getMvps());
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        objCon.coleccion.update(query, updateObject);
        return true;
    }
    
    public List<Perfil> obtenerJugadoresPorPosicion(String posicion) {
    List<Perfil> jugadores = new ArrayList<>();
    Conexion objCon = new Conexion();

    // Consulta para obtener jugadores con la posición especificada
    BasicDBObject query = new BasicDBObject();
    query.put("POSICION", posicion);  // Filtrar por la posición seleccionada

    DBCursor cursor = objCon.coleccion.find(query);
    while (cursor.hasNext()) {
        DBObject doc = cursor.next();
        String nombre = (String) doc.get("NOMBRE");
        String pos = (String) doc.get("POSICION");
        Float altura = ((Double) doc.get("ALTURA")).floatValue();
        Float peso = ((Double) doc.get("PESO")).floatValue();
        int edad = ((Number) doc.get("EDAD")).intValue();

        // Crear el perfil de cada jugador encontrado y agregarlo a la lista
        Perfil jugador = new Perfil(nombre, pos, altura, peso, edad);
        jugadores.add(jugador);
    }
    return jugadores;
}

}