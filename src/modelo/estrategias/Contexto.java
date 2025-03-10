package modelo.estrategias;

public class Contexto {

    private Estrategias estrategias;

    // Constructor con estrategia por defecto
    public Contexto(Estrategias estrategias) {
        if (estrategias == null) {
            throw new IllegalArgumentException("La estrategia no puede ser nula.");
        }
        this.estrategias = estrategias;
    }

    public Contexto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para cambiar la estrategia
    public void setEstrategia(Estrategias estrategias) {
        if (estrategias == null) {
            throw new IllegalArgumentException("La estrategia no puede ser nula.");
        }
        this.estrategias = estrategias;
    }

    // Método para obtener la estrategia actual
    public Estrategias getEstrategia() {
        return estrategias;
    }

    // Método para ejecutar la estrategia
    public void ejecutarEstrategia() {
        estrategias.ejecutar();
    }
}