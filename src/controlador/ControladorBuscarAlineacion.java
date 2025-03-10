package controlador;

import vista.FrmActualizarJugador;
import vista.FrmReporte;
import modelo.perfiles.Perfil;
import modelo.perfiles.PerfilDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import vista.FrmPartido;

public class ControladorBuscarAlineacion implements ActionListener {

    private FrmReporte vista;

    public ControladorBuscarAlineacion(FrmReporte vista) {
        this.vista = vista;
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscar) {
            String nombreAlineacion = (String) vista.alineacion.getSelectedItem();
            if (nombreAlineacion == null || nombreAlineacion.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una alineación.");
                return;
            }

            PerfilDAO perfilDAO = new PerfilDAO();
            List<Perfil> jugadores = perfilDAO.obtenerJugadoresPorAlineacion(nombreAlineacion);

            if (jugadores.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No se encontraron jugadores para la alineación: " + nombreAlineacion);
                return;
            }

            for (Perfil jugador : jugadores) {
                FrmActualizarJugador frmActualizarJugador = new FrmActualizarJugador();
                frmActualizarJugador.cbJugador.setSelectedItem(jugador.getNombre());
                frmActualizarJugador.setVisible(true);
                frmActualizarJugador.setLocationRelativeTo(null);
            }
        } else if (e.getSource() == vista.btnAtras) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de partidos (FrmPartido)
            FrmPartido frmPartido = new FrmPartido();
            frmPartido.setVisible(true);
            frmPartido.setLocationRelativeTo(null);

            // Pasar el control al ControladorPartido
            new ControladorPartido(frmPartido);
        }
    }
}