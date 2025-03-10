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
import vista.FrmNuevoA;

public class ControladorNuevaAlineacion implements ActionListener {

    private FrmNuevoA vistaNuevoA;
    private AlineacionDAO aDAO;
    private AbstractFactory factory;

    public ControladorNuevaAlineacion(FrmNuevoA vista) {
        this.vistaNuevoA = vista;
        this.factory = new BasicFactory(); // Usar la fábrica concreta
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaNuevoA.btnCrearAlineacion) {
            String nombreAlineacion = vistaNuevoA.txtNombreA.getText();
            Alineacion alineacion = factory.crearAlineacion(nombreAlineacion);

            // Obtener jugadores seleccionados (esto es un ejemplo, debes adaptarlo a tu interfaz)
            List<Perfil> jugadoresSeleccionados = aDAO.obtenerJugadoresPorPosicion("Base"); // Ejemplo: obtener jugadores de la posición "Base"

            for (Perfil jugador : jugadoresSeleccionados) {
                alineacion.agregarComponente(jugador); // Agregar el perfil directamente como componente
            }

            // Guardar la alineación en la base de datos
            aDAO.crearAlineacion(alineacion);

            JOptionPane.showMessageDialog(vistaNuevoA, "Alineación creada correctamente.");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        vistaNuevoA.txtNombreA.setText("");
    }
}