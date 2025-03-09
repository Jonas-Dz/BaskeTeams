package modelo.alineaciones;

import java.util.ArrayList;
import java.util.List;

public class Alineacion implements Componente {
    private String nombre;
    private List<Componente> componentes;

    public Alineacion(String nombre) {
        this.nombre = nombre;
        this.componentes = new ArrayList<>();
    }

    public void agregarComponente(Componente componente) {
        componentes.add(componente);
    }

    public void eliminarComponente(Componente componente) {
        componentes.remove(componente);
    }

    @Override
    public void mostrar() {
        System.out.println("Alineación: " + nombre);
        for (Componente componente : componentes) {
            componente.mostrar();
        }
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}