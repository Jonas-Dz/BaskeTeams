package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;

public class ControladorPrincipal implements ActionListener {

    private FrmPrincipal vista;

    public ControladorPrincipal(FrmPrincipal vista) {
        this.vista = vista;

        // Registrar eventos de botones
        this.vista.btnPerfiles.addActionListener(this);
        this.vista.btnAlineaciones.addActionListener(this);
        this.vista.btnPartidos.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnPerfiles) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de perfiles
            FrmPerfiles perfiles = new FrmPerfiles();
            perfiles.setVisible(true);
            perfiles.setLocationRelativeTo(null);

            // Pasar el control al ControladorPerfiles
            new ControladorPerfiles(perfiles);
        }

        if (e.getSource() == vista.btnAlineaciones) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de alineaciones
            FrmAlineacion alineaciones = new FrmAlineacion();
            alineaciones.setVisible(true);
            alineaciones.setLocationRelativeTo(null);

            // Pasar el control al ControladorAlineacion
            new ControladorAlineacion(alineaciones);
        }

        if (e.getSource() == vista.btnPartidos) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de partidos
            FrmPartido partido = new FrmPartido();
            partido.setVisible(true);
            partido.setLocationRelativeTo(null);

            // Pasar el control al ControladorPartido
            new ControladorPartido(partido);
        }
    }
}