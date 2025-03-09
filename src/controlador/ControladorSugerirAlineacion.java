package controlador;

import modelo.estrategias.Contexto;
import modelo.estrategias.SugerirAlineacion;
import vista.FrmSugerir;

public class ControladorSugerirAlineacion {

    private Contexto contexto;

    public ControladorSugerirAlineacion(FrmSugerir sugerirAlineacion) {
        this.contexto = new Contexto();
    }

    public void sugerirAlineacion() {
        contexto.setEstrategia(new SugerirAlineacion());
        contexto.ejecutarEstrategia();
    }
}