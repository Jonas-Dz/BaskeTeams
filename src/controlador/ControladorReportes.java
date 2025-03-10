package controlador;

import modelo.estrategias.Contexto;
import modelo.estrategias.GenerarReportePostPartido;

public class ControladorReportes {

    private Contexto contexto;

    public ControladorReportes() {
        this.contexto = new Contexto();
    }

    public void generarReportePostPartido() {
        contexto.setEstrategia(new GenerarReportePostPartido());
        contexto.ejecutarEstrategia();
    }}