package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.estadisticas.EstadisticasJugador;
import modelo.estadisticas.EstadisticasDAO;
import modelo.perfiles.Perfil;
import modelo.perfiles.PerfilDAO;
import vista.FrmCompararJugador;
import vista.FrmPerfiles;

public class ControladorCompararJugador implements ActionListener {
    private FrmCompararJugador vistaComparar;

    public ControladorCompararJugador(FrmCompararJugador vistaComparar) {
        this.vistaComparar = vistaComparar;
        this.vistaComparar.btnComparar.addActionListener(this);
        this.vistaComparar.btnRegresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaComparar.btnComparar) {
            compararPerfiles();
        }
        if (e.getSource() == vistaComparar.btnRegresar){
            vistaComparar.dispose();
            // Abrir la ventana de perfiles (FrmPerfiles)
            FrmPerfiles frmPerfiles = new FrmPerfiles();
            frmPerfiles.setVisible(true);
            frmPerfiles.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

            // Pasar el control al ControladorPerfiles
            new ControladorPerfiles(frmPerfiles);
        }
    }

    private void compararPerfiles() {
        String perfil1 = (String) vistaComparar.cbPerfil1.getSelectedItem();
        String perfil2 = (String) vistaComparar.cbPerfil2.getSelectedItem();
        
        if (perfil1 == null || perfil2 == null || perfil1.isEmpty() || perfil2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione dos perfiles.");
            return;
        }
        
        if (perfil1.equals(perfil2)) {
            JOptionPane.showMessageDialog(null, "Seleccione dos perfiles diferentes.");
            return;
        }
        
        EstadisticasDAO estadisticasDAO = new EstadisticasDAO();
        EstadisticasJugador e1 = estadisticasDAO.obtenerEstadisticas(perfil1);
        EstadisticasJugador e2 = estadisticasDAO.obtenerEstadisticas(perfil2);
        
        if (e1 == null && e2 == null) {
            JOptionPane.showMessageDialog(null, "Ambos perfiles no poseen ninguna estadística.");
            clearLabels();
            return;
        } else if (e1 == null) {
            JOptionPane.showMessageDialog(null, "El perfil " + perfil1 + " no posee ninguna estadística.");
            clearLabels();
            return;
        } else if (e2 == null) {
            JOptionPane.showMessageDialog(null, "El perfil " + perfil2 + " no posee ninguna estadística.");
            clearLabels();
            return;
        }

        // Set statistics for profile 1
        vistaComparar.lblRebotes1.setText(String.valueOf(e1.getRebotes()));
        vistaComparar.lblBloqueos1.setText(String.valueOf(e1.getBloqueos()));
        vistaComparar.lblAsistencias1.setText(String.valueOf(e1.getAsistencias()));
        vistaComparar.lblPuntos1.setText(String.valueOf(e1.getPuntos()));
        vistaComparar.lblRobos1.setText(String.valueOf(e1.getRobos()));
        vistaComparar.lblPj1.setText(String.valueOf(e1.getPartidosJugados()));
        vistaComparar.lblMVP1.setText(String.valueOf(e1.getMvps()));

        // Set statistics for profile 2
        vistaComparar.lblRebotes2.setText(String.valueOf(e2.getRebotes()));
        vistaComparar.lblBloqueos2.setText(String.valueOf(e2.getBloqueos()));
        vistaComparar.lblAsistencias2.setText(String.valueOf(e2.getAsistencias()));
        vistaComparar.lblPuntos2.setText(String.valueOf(e2.getPuntos()));
        vistaComparar.lblRobos2.setText(String.valueOf(e2.getRobos()));
        vistaComparar.lblPj2.setText(String.valueOf(e2.getPartidosJugados()));
        vistaComparar.lblMVP2.setText(String.valueOf(e2.getMvps()));

        // Calculate and set total points for profile 1
        int totalPoints1 = e1.getRebotes() + e1.getBloqueos() + e1.getAsistencias() + e1.getPuntos() + e1.getRobos() + e1.getPartidosJugados() + e1.getMvps();
        vistaComparar.lblPt1.setText(String.valueOf(totalPoints1));

        // Calculate and set total points for profile 2
        int totalPoints2 = e2.getRebotes() + e2.getBloqueos() + e2.getAsistencias() + e2.getPuntos() + e2.getRobos() + e2.getPartidosJugados() + e2.getMvps();
        vistaComparar.lblPt2.setText(String.valueOf(totalPoints2));

        // Determine the profile with the highest total points
        PerfilDAO perfilDAO = new PerfilDAO();
        Perfil mejorPerfil;
        if (totalPoints1 > totalPoints2) {
            mejorPerfil = perfilDAO.obtenerPerfilPorNombre(perfil1);
        } else {
            mejorPerfil = perfilDAO.obtenerPerfilPorNombre(perfil2);
        }

        // Set the name and position of the best profile
        if (mejorPerfil != null) {
            vistaComparar.lblNombre.setText(mejorPerfil.getNombre());
            vistaComparar.lblPos.setText(mejorPerfil.getPosicion());
        }
    }

    private void clearLabels() {
        vistaComparar.lblRebotes1.setText("");
        vistaComparar.lblBloqueos1.setText("");
        vistaComparar.lblAsistencias1.setText("");
        vistaComparar.lblPuntos1.setText("");
        vistaComparar.lblRobos1.setText("");
        vistaComparar.lblPj1.setText("");
        vistaComparar.lblMVP1.setText("");
        vistaComparar.lblPt1.setText("");
        vistaComparar.lblRebotes2.setText("");
        vistaComparar.lblBloqueos2.setText("");
        vistaComparar.lblAsistencias2.setText("");
        vistaComparar.lblPuntos2.setText("");
        vistaComparar.lblRobos2.setText("");
        vistaComparar.lblPj2.setText("");
        vistaComparar.lblMVP2.setText("");
        vistaComparar.lblPt2.setText("");
        vistaComparar.lblNombre.setText("");
        vistaComparar.lblPos.setText("");
    }
}