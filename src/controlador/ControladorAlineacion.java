package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;

public class ControladorAlineacion implements ActionListener {

    private FrmAlineacion vista;

    public ControladorAlineacion(FrmAlineacion vista) {
        this.vista = vista;

        // Registrar eventos de botones
        this.vista.btnNuevoA.addActionListener(this);
        this.vista.btnModificarA.addActionListener(this);
        this.vista.btnSugerirA.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnNuevoA) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de nueva alineación
            FrmNuevoA nuevaAlineacion = new FrmNuevoA();
            nuevaAlineacion.setVisible(true);
            nuevaAlineacion.setLocationRelativeTo(null);

            // Pasar el control al ControladorNuevaAlineacion
            new ControladorNuevaAlineacion(nuevaAlineacion);
        }

        if (e.getSource() == vista.btnModificarA) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de modificar alineación
            FrmModificarAlineacion modificarAlineacion = new FrmModificarAlineacion();
            modificarAlineacion.setVisible(true);
            modificarAlineacion.setLocationRelativeTo(null);

            // Pasar el control al ControladorModificarAlineacion
            new ControladorModificarAlineacion(modificarAlineacion);
        }

        if (e.getSource() == vista.btnSugerirA) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de sugerir alineación
            FrmSugerir sugerirAlineacion = new FrmSugerir();
            sugerirAlineacion.setVisible(true);
            sugerirAlineacion.setLocationRelativeTo(null);

            // Pasar el control al ControladorSugerir
            new ControladorSugerirAlineacion(sugerirAlineacion);
        }
    }
}