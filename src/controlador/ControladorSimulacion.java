package controlador;

import modelo.estrategias.Contexto;
import modelo.estrategias.SimulacionPartido;

public class ControladorSimulacion {

    private Contexto contexto;

    public ControladorSimulacion() {
        this.contexto = new Contexto();
    }

    public void simularPartido() {
        contexto.setEstrategia(new SimulacionPartido());
        contexto.ejecutarEstrategia();
    }
}