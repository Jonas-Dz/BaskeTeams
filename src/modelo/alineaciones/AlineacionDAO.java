package modelo.alineaciones;

import modelo.perfiles.Perfil;
import modelo.conexiones.Conexion;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import modelo.Observer.Sujeto;

public class AlineacionDAO extends Sujeto {
    private Conexion conexion;

    public AlineacionDAO() {
        this.conexion = Conexion.getInstancia();
    }

    public List<Perfil> obtenerJugadoresPorPosicion(String posicion) {
        List<Perfil> jugadores = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("POSICION", posicion);

        DBCursor cursor = conexion.getColeccionPerfiles().find(query);
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

        notificarObservadores("Se han obtenido jugadores para la posición: " + posicion);
        return jugadores;
    }

    public Perfil obtenerJugadorPorPosicion(String posicion) {
        List<Perfil> jugadores = obtenerJugadoresPorPosicion(posicion);
        if (!jugadores.isEmpty()) {
            return jugadores.get(0); // Return the first player found
        }
        return null;
    }

    public void crearAlineacion(Alineacion alineacion) {
        BasicDBObject documento = new BasicDBObject();
        documento.put("NOMBRE", alineacion.getNombre());

        List<BasicDBObject> jugadoresDocumento = new ArrayList<>();
        for (Componente componente : alineacion.getComponentes()) {
            if (componente instanceof Perfil) {
                Perfil jugador = (Perfil) componente;
                BasicDBObject jugadorDoc = new BasicDBObject();
                jugadorDoc.put("NOMBRE", jugador.getNombre());
                jugadorDoc.put("POSICION", jugador.getPosicion());
                jugadorDoc.put("ALTURA", jugador.getAltura());
                jugadorDoc.put("PESO", jugador.getPeso());
                jugadorDoc.put("EDAD", jugador.getEdad());
                jugadoresDocumento.add(jugadorDoc);
            }
        }
        documento.put("JUGADORES", jugadoresDocumento);

        conexion.getColeccionAlineaciones().insert(documento);
        notificarObservadores("Se ha creado una nueva alineación: " + alineacion.getNombre());
    }
<<<<<<< HEAD

    public Alineacion obtenerAlineacionPorNombre(String nombre) {
        BasicDBObject query = new BasicDBObject();
        query.put("NOMBRE", nombre);
        DBObject doc = conexion.getColeccionAlineaciones().findOne(query);
        if (doc != null) {
            Alineacion alineacion = new Alineacion((String) doc.get("NOMBRE"));
            List<DBObject> jugadoresDoc = (List<DBObject>) doc.get("JUGADORES");
            for (DBObject jugadorDoc : jugadoresDoc) {
                String nombreJugador = (String) jugadorDoc.get("NOMBRE");
                String posicion = (String) jugadorDoc.get("POSICION");
                // Assuming other fields are not necessary for this check
                Perfil jugador = new Perfil(nombreJugador, posicion, 0f, 0f, 0);
                alineacion.agregarComponente(jugador);
            }
            return alineacion;
        }
        return null;
    }

    public List<Alineacion> obtenerTodasAlineaciones() {
=======
    
    public List<Perfil> obtenerJugadoresPorAlineacion(String nombreAlineacion) {
        List<Perfil> jugadores = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("NOMBRE", nombreAlineacion);

        DBObject doc = conexion.getColeccionAlineaciones().findOne(query);
        if (doc != null) {
            List<DBObject> jugadoresDoc = (List<DBObject>) doc.get("JUGADORES");
            for (DBObject jugadorDoc : jugadoresDoc) {
                String nombre = (String) jugadorDoc.get("NOMBRE");
                String posicion = (String) jugadorDoc.get("POSICION");
                Float altura = ((Double) jugadorDoc.get("ALTURA")).floatValue();
                Float peso = ((Double) jugadorDoc.get("PESO")).floatValue();
                int edad = ((Number) jugadorDoc.get("EDAD")).intValue();

                Perfil jugador = new Perfil(nombre, posicion, altura, peso, edad);
                jugadores.add(jugador);
            }
        }

        return jugadores;
    }
    
    // Método para obtener todas las alineaciones
    public List<Alineacion> obtenerAlineaciones() {
>>>>>>> 531460096b5224d4b04c1750b8b7c01e15acd3c5
        List<Alineacion> alineaciones = new ArrayList<>();
        DBCursor cursor = conexion.getColeccionAlineaciones().find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
<<<<<<< HEAD
            Alineacion alineacion = new Alineacion((String) doc.get("NOMBRE"));
            List<DBObject> jugadoresDoc = (List<DBObject>) doc.get("JUGADORES");
            for (DBObject jugadorDoc : jugadoresDoc) {
                String nombreJugador = (String) jugadorDoc.get("NOMBRE");
                String posicion = (String) jugadorDoc.get("POSICION");
                Perfil jugador = new Perfil(nombreJugador, posicion, 0f, 0f, 0);
                alineacion.agregarComponente(jugador);
            }
=======
            String nombre = (String) doc.get("NOMBRE");

            Alineacion alineacion = new Alineacion(nombre);
>>>>>>> 531460096b5224d4b04c1750b8b7c01e15acd3c5
            alineaciones.add(alineacion);
        }
        return alineaciones;
    }
<<<<<<< HEAD
=======
    
>>>>>>> 531460096b5224d4b04c1750b8b7c01e15acd3c5
}