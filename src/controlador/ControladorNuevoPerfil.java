package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.perfiles.Perfil;
import modelo.perfiles.PerfilDAO;
import modelo.factories.AbstractFactory;
import modelo.factories.BasicFactory;
import vista.FrmNuevoPerfil;
import vista.FrmPerfiles;

public class ControladorNuevoPerfil implements ActionListener {

    private FrmNuevoPerfil vista;
    private PerfilDAO pDAO;
    private AbstractFactory factory;

    public ControladorNuevoPerfil(FrmNuevoPerfil vista) {
        this.vista = vista;
        this.pDAO = new PerfilDAO();
        this.factory = new BasicFactory(); // Usar la fábrica concreta

        // Registrar eventos de botones
        this.vista.btnSiguiente.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnSiguiente) {
            try {
                String nombre = vista.txtNombre.getText();
                String posicion = vista.comboPos.getSelectedItem().toString();
                
                // Validar que los campos de altura, peso y edad contengan números
                if (!isNumeric(vista.txtAltura.getText()) || !isNumeric(vista.txtPeso.getText()) || !isNumeric(vista.txtEdad.getText())) {
                    throw new NumberFormatException();
                }

                float altura = Float.parseFloat(vista.txtAltura.getText());
                float peso = Float.parseFloat(vista.txtPeso.getText());
                int edad = Integer.parseInt(vista.txtEdad.getText());

                // Validar altura
                if (altura < 1.50 || altura > 2.10) {
                    vista.txtAltura.setText("");
                    throw new IllegalArgumentException("La altura debe estar entre 1.50m y 2.10m.");
                }

                // Validar peso
                if (peso <= 30.0) {
                    vista.txtPeso.setText("");
                    throw new IllegalArgumentException("El peso debe ser mayor que 30 kg.");
                }

                // Crear Perfil usando la fábrica abstracta
                Perfil perfil = factory.crearJugador(nombre, posicion, altura, peso, edad);

                // Añadir Perfil a la base de datos
                pDAO.crearPerfil(perfil);

                JOptionPane.showMessageDialog(vista, "Perfil creado correctamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Solo puede ingresar números en peso, altura y edad.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al crear el perfil: " + ex.getMessage());
            }
        }

        if (e.getSource() == vista.btnAtras) {
            // Cerrar la ventana actual (FrmNuevoPerfil)
            vista.dispose();

            // Abrir la ventana de perfiles (FrmPerfiles)
            FrmPerfiles frmPerfiles = new FrmPerfiles();
            frmPerfiles.setVisible(true);
            frmPerfiles.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

            // Pasar el control al ControladorPerfiles
            new ControladorPerfiles(frmPerfiles);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.comboPos.setSelectedIndex(0);
        vista.txtAltura.setText("");
        vista.txtPeso.setText("");
        vista.txtEdad.setText("");
    }
}