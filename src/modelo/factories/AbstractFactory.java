package modelo.factories;

import modelo.alineaciones.Alineacion;
import modelo.perfiles.Perfil;

public interface AbstractFactory {
    Perfil crearJugador(String nombre, String posicion, float altura, float peso, int edad);
    Alineacion crearAlineacion(String nombre);
}