package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.alineaciones.Alineacion;
import modelo.alineaciones.AlineacionDAO;
import modelo.factories.AbstractFactory;
import modelo.factories.BasicFactory;
import modelo.perfiles.Perfil;
import modelo.perfiles.PerfilDAO;
import vista.FrmAlineacion;
import vista.FrmNuevoA;

public class ControladorNuevaAlineacion implements ActionListener {

    private FrmNuevoA vistaNuevoA;
    private AlineacionDAO aDAO;
    private AbstractFactory factory;
    private PerfilDAO perfilDAO;

    public ControladorNuevaAlineacion(FrmNuevoA vista) {
        this.vistaNuevoA = vista;
        this.aDAO = new AlineacionDAO(); // Initialize AlineacionDAO
        this.factory = new BasicFactory(); // Usar la fábrica concreta
        this.perfilDAO = new PerfilDAO(); // Initialize PerfilDAO
        this.vistaNuevoA.btnAtrasA.addActionListener(this);
        this.vistaNuevoA.btnNextA.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaNuevoA.btnNextA) {
            String nombreAlineacion = vistaNuevoA.txtNombreA.getText();
            Alineacion alineacion = factory.crearAlineacion(nombreAlineacion);

            // Check if an alignment with the same name already exists
            Alineacion existingAlineacion = aDAO.obtenerAlineacionPorNombre(nombreAlineacion);
            if (existingAlineacion != null) {
                int response = JOptionPane.showConfirmDialog(vistaNuevoA, 
                    "La alineación con el nombre '" + nombreAlineacion + "' ya existe. ¿Desea sobrescribir los datos?", 
                    "Confirmar Sobrescritura", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);
                if (response != JOptionPane.YES_OPTION) {
                    return; // If the user chooses not to overwrite, exit the method
                }
            }

            // Obtener jugadores seleccionados de los ComboBoxes
            Perfil base = perfilDAO.obtenerPerfilPorNombre((String) vistaNuevoA.comboBase.getSelectedItem());
            Perfil escolta = perfilDAO.obtenerPerfilPorNombre((String) vistaNuevoA.comboEscolta.getSelectedItem());
            Perfil alero = perfilDAO.obtenerPerfilPorNombre((String) vistaNuevoA.comboAlero.getSelectedItem());
            Perfil apivot = perfilDAO.obtenerPerfilPorNombre((String) vistaNuevoA.comboAPivot.getSelectedItem());
            Perfil pivot = perfilDAO.obtenerPerfilPorNombre((String) vistaNuevoA.comboPivot.getSelectedItem());

            // Agregar los perfiles a la alineación
            if (base != null) alineacion.agregarComponente(base);
            if (escolta != null) alineacion.agregarComponente(escolta);
            if (alero != null) alineacion.agregarComponente(alero);
            if (apivot != null) alineacion.agregarComponente(apivot);
            if (pivot != null) alineacion.agregarComponente(pivot);

            // Guardar la alineación en la base de datos
            aDAO.crearAlineacion(alineacion);

            JOptionPane.showMessageDialog(vistaNuevoA, "Alineación creada correctamente.");
            limpiarCampos();
        }
        if(e.getSource() == vistaNuevoA.btnAtrasA){
            vistaNuevoA.dispose();
            
            // Abrir la ventana Alineaciones (FrmAlineacion)
            FrmAlineacion frmAlineacion = new FrmAlineacion();
            frmAlineacion.setVisible(true);
            frmAlineacion.setLocationRelativeTo(null);
            new ControladorAlineacion(frmAlineacion);
        }
    }

    private void limpiarCampos() {
        vistaNuevoA.txtNombreA.setText("");
    }
}