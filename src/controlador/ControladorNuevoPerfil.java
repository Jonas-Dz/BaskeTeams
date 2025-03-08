package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Perfil;
import modelo.PerfilDAO;
import vista.FrmNuevoPerfil;

public class ControladorNuevoPerfil implements ActionListener {

    private FrmNuevoPerfil vistaNuevoPerfil;
    private PerfilDAO pDAO;

    public ControladorNuevoPerfil(FrmNuevoPerfil vista, PerfilDAO dao) {
        this.vistaNuevoPerfil = vista;
        this.pDAO = dao;
        // Asignar listeners a los botones
        vistaNuevoPerfil.btnSiguiente.addActionListener(this);
        vistaNuevoPerfil.btnAtras.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaNuevoPerfil.btnSiguiente) {
            try {
                String nombre = vistaNuevoPerfil.txtNombre.getText();
                String posicion = vistaNuevoPerfil.comboPos.getSelectedItem().toString();
                Float altura = Float.parseFloat(vistaNuevoPerfil.txtAltura.getText());
                Float peso = Float.parseFloat(vistaNuevoPerfil.txtPeso.getText());
                int edad = Integer.parseInt(vistaNuevoPerfil.txtEdad.getText());

                // Validar altura
                if (altura < 1.50 || altura > 2.10) {
                    vistaNuevoPerfil.txtAltura.setText("");
                    throw new IllegalArgumentException("Estatura debe estar entre 1.50m y 2.10m.");
                }
                // Validar peso
                if (peso <= 30.0) {
                    vistaNuevoPerfil.txtPeso.setText("");
                    throw new IllegalArgumentException("El peso debe ser mayor que 30 kg.");
                }

                // Crear Perfil
                Perfil perf = new Perfil(nombre, posicion, altura, peso, edad);
                // Añadir Perfil a la base de datos
                pDAO.crearPerfil(perf);
                JOptionPane.showMessageDialog(vistaNuevoPerfil, "Perfil creado correctamente.");
                limpiarCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaNuevoPerfil, "Solo puede ingresar números en peso, altura y edad.");
            }
        }
        if (e.getSource() == vistaNuevoPerfil.btnAtras) {
            vistaNuevoPerfil.dispose();
        }
    }

    private void limpiarCampos() {
        vistaNuevoPerfil.txtNombre.setText("");
        vistaNuevoPerfil.comboPos.setSelectedIndex(0);
        vistaNuevoPerfil.txtAltura.setText("");
        vistaNuevoPerfil.txtPeso.setText("");
        vistaNuevoPerfil.txtEdad.setText("");
    }
}