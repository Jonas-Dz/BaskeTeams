package modelo;
/**
 *
 * @author danip
 */
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Conexion {
    DB baseDatos;
    DBCollection coleccion;
    DBCollection coleccion2;
    
    public Conexion() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        baseDatos = mongo.getDB("basketeams");
        coleccion = baseDatos.getCollection("perfiles");
        coleccion2 = baseDatos.getCollection("alineaciones");
        System.out.println("Conexion BD exitosa");
    }
}

//Implementar singleton
