package modelo.conexiones;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Conexion {
    private static Conexion instancia; // Instancia única de la clase
    private DB baseDatos;
    private DBCollection coleccion;
    private DBCollection coleccion2;

    // Constructor privado para evitar instanciación externa
    private Conexion() {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            baseDatos = mongo.getDB("basketeams");
            coleccion = baseDatos.getCollection("perfiles");
            coleccion2 = baseDatos.getCollection("alineaciones");
            System.out.println("Conexión a la base de datos exitosa");
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método estático para obtener la instancia única
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Getters para acceder a las colecciones
    public DBCollection getColeccionPerfiles() {
        return coleccion;
    }

    public DBCollection getColeccionAlineaciones() {
        return coleccion2;
    }
}
