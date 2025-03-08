package modelo;

import java.util.ArrayList;
import java.util.List;

public class Sujeto {
    private List<Observer> observadores = new ArrayList<>();

    // Método para agregar observadores
    public void agregarObservador(Observer observer) {
        observadores.add(observer);
    }

    // Método para eliminar observadores
    public void eliminarObservador(Observer observer) {
        observadores.remove(observer);
    }

    // Método para notificar a los observadores
    public void notificarObservadores(String mensaje) {
        for (Observer observer : observadores) {
            observer.actualizar(mensaje);
        }
    }
}
