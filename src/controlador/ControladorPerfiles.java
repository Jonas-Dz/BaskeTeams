package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Perfil;
import modelo.PerfilDAO;
import vista.FrmNuevoPerfil;
import vista.FrmPerfiles;

public class ControladorPerfiles implements ActionListener {

    private FrmPerfiles vistaPerfil;
    private FrmNuevoPerfil vistaNuevoPerfil;
    private PerfilDAO pDAO;

    public ControladorPerfiles(FrmPerfiles vista) {
        this.vistaPerfil = vista;
        this.pDAO = new PerfilDAO();
        // Asignar listeners a los botones
        vistaPerfil.btnNuevoP.addActionListener(this);
        vistaPerfil.btnActualizarP.addActionListener(this);
        vistaPerfil.btnCompararP.addActionListener(this);
        vistaPerfil.btnMenu.addActionListener(this);
    }

    public ControladorPerfiles() {
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPerfil.btnNuevoP) {
            vistaNuevoPerfil = new FrmNuevoPerfil();
            vistaNuevoPerfil.setVisible(true);
            vistaNuevoPerfil.setLocationRelativeTo(null);
            new ControladorNuevoPerfil(vistaNuevoPerfil, pDAO); // Pasar el control a ControladorNuevoPerfil
        }
    }
}