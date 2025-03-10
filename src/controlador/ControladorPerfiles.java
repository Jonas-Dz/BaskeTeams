package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FrmPerfiles;
import vista.FrmNuevoPerfil;
import vista.FrmActualizarJugador;
import vista.FrmCompararJugador;
import vista.FrmPrincipal;

public class ControladorPerfiles implements ActionListener {

    private FrmPerfiles vista;

    public ControladorPerfiles(FrmPerfiles vista) {
        this.vista = vista;

        // Registrar eventos de botones
        this.vista.btnNuevoP.addActionListener(this);
        this.vista.btnActualizarP.addActionListener(this);
        this.vista.btnCompararP.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnNuevoP) {
            // Cerrar la ventana actual (FrmPerfiles)
            vista.dispose();

            // Abrir la ventana de nuevo perfil (FrmNuevoPerfil)
            FrmNuevoPerfil frmNuevoPerfil = new FrmNuevoPerfil();
            frmNuevoPerfil.setVisible(true);
            frmNuevoPerfil.setLocationRelativeTo(null);

            // Pasar el control al ControladorNuevoPerfil (si existe)
            //new ControladorNuevoPerfil(frmNuevoPerfil);
        }

        if (e.getSource() == vista.btnActualizarP) {
            // Cerrar la ventana actual (FrmPerfiles)
            vista.dispose();

            // Abrir la ventana de actualizar jugador (FrmActualizarJugador)
            FrmActualizarJugador frmActualizarJugador = new FrmActualizarJugador();
            frmActualizarJugador.setVisible(true);
            frmActualizarJugador.setLocationRelativeTo(null);

            // Pasar el control al ControladorActualizarJugador (si existe)
            // new ControladorActualizarJugador(frmActualizarJugador);
        }

        if (e.getSource() == vista.btnCompararP) {
            // Cerrar la ventana actual (FrmPerfiles)
            vista.dispose();

            // Abrir la ventana de comparar jugadores (FrmCompararJugador)
            FrmCompararJugador frmCompararJugador = new FrmCompararJugador();
            frmCompararJugador.setVisible(true);
            frmCompararJugador.setLocationRelativeTo(null);

            // Pasar el control al ControladorCompararJugador (si existe)
            // new ControladorCompararJugador(frmCompararJugador);
        }

        if (e.getSource() == vista.btnMenu) {
            // Cerrar la ventana actual (FrmPerfiles)
            vista.dispose();

            // Abrir el menú principal (FrmPrincipal)
            FrmPrincipal frmPrincipal = new FrmPrincipal();
            frmPrincipal.setVisible(true);
            frmPrincipal.setLocationRelativeTo(null);

            // Pasar el control al ControladorPrincipal
            new ControladorPrincipal(frmPrincipal);
        }
    }
}