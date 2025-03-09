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
}