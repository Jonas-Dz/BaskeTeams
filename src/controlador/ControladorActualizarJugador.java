/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.estadisticas.EstadisticasDAO;
import modelo.estadisticas.EstadisticasJugador;
import vista.FrmActualizarJugador;
import modelo.conexiones.Conexion;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.perfiles.Perfil;
import modelo.perfiles.PerfilDAO;
import vista.FrmPerfiles;

/**
 *
 * @author danip
 */
public class ControladorActualizarJugador implements ActionListener {
    private FrmActualizarJugador vista;
    private EstadisticasDAO estadisticasDAO;
    private PerfilDAO perfilDAO;

    public ControladorActualizarJugador(FrmActualizarJugador vista) {
        this.vista = vista;
        this.estadisticasDAO = new EstadisticasDAO();
        this.perfilDAO = new PerfilDAO();
        this.vista.cbJugador.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this); // Add action listener for btnRegresar
        this.vista.btnEliminar.addActionListener(this); // Add action listener for btnEliminar
        llenarComboObjetos();
    }

    private void llenarComboObjetos() {
        PerfilDAO perfilDAO = new PerfilDAO();  // Crear instancia del DAO
        List<Perfil> perfiles = perfilDAO.obtenerPerfiles(); // Obtener lista de jugadores

        vista.cbJugador.removeAllItems(); // Limpiar el ComboBox antes de llenarlo

        for (Perfil perfil : perfiles) {
            vista.cbJugador.addItem(perfil.getNombre()); // Agregar los nombres al ComboBox
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardarEstadisticas();
        }
        if (e.getSource() == vista.btnRegresar) {
            vista.dispose();

            // Abrir la ventana de perfiles (FrmPerfiles)
            FrmPerfiles frmPerfiles = new FrmPerfiles();
            frmPerfiles.setVisible(true);
            frmPerfiles.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

            // Pasar el control al ControladorPerfiles
            new ControladorPerfiles(frmPerfiles);
        }
        if (e.getSource() == vista.btnEliminar) {
            eliminarPerfil();
        }
    }

    private void limpiarCampos() {
        vista.txtRebotesTotales.setText("");
        vista.txtBloqueosTotales.setText("");
        vista.txtAsistenciasTotales.setText("");
        vista.txtPuntosTotales.setText("");
        vista.txtRobosTotales.setText("");
        vista.txtPartidosJugados.setText("");
        vista.txtMVPsTotales.setText("");
    }

    private void guardarEstadisticas() {
        try {
            if (vista.txtRebotesTotales.getText().isEmpty() || vista.txtBloqueosTotales.getText().isEmpty() ||
                vista.txtAsistenciasTotales.getText().isEmpty() || vista.txtPuntosTotales.getText().isEmpty() ||
                vista.txtRobosTotales.getText().isEmpty() || vista.txtPartidosJugados.getText().isEmpty() ||
                vista.txtMVPsTotales.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos deben estar llenos.");
                return;
            }

            String nombreJugador = (String) vista.cbJugador.getSelectedItem();
            int rebotes = Integer.parseInt(vista.txtRebotesTotales.getText());
            int bloqueos = Integer.parseInt(vista.txtBloqueosTotales.getText());
            int asistencias = Integer.parseInt(vista.txtAsistenciasTotales.getText());
            int puntos = Integer.parseInt(vista.txtPuntosTotales.getText());
            int robos = Integer.parseInt(vista.txtRobosTotales.getText());
            int partidosJugados = Integer.parseInt(vista.txtPartidosJugados.getText());
            int mvps = Integer.parseInt(vista.txtMVPsTotales.getText());

            EstadisticasJugador estadisticasNuevas = new EstadisticasJugador(rebotes, bloqueos, asistencias, puntos, robos, partidosJugados, mvps);
            EstadisticasJugador estadisticasExistentes = estadisticasDAO.obtenerEstadisticas(nombreJugador);

            if (estadisticasExistentes != null) {
                estadisticasNuevas.setRebotes(estadisticasNuevas.getRebotes() + estadisticasExistentes.getRebotes());
                estadisticasNuevas.setBloqueos(estadisticasNuevas.getBloqueos() + estadisticasExistentes.getBloqueos());
                estadisticasNuevas.setAsistencias(estadisticasNuevas.getAsistencias() + estadisticasExistentes.getAsistencias());
                estadisticasNuevas.setPuntos(estadisticasNuevas.getPuntos() + estadisticasExistentes.getPuntos());
                estadisticasNuevas.setRobos(estadisticasNuevas.getRobos() + estadisticasExistentes.getRobos());
                estadisticasNuevas.setPartidosJugados(estadisticasNuevas.getPartidosJugados() + estadisticasExistentes.getPartidosJugados());
                estadisticasNuevas.setMvps(estadisticasNuevas.getMvps() + estadisticasExistentes.getMvps());
            }

            estadisticasDAO.crearEstadisticas(nombreJugador, estadisticasNuevas);
            JOptionPane.showMessageDialog(vista, "Estadísticas actualizadas correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Solo se admiten números enteros.");
        }
    }

    private void eliminarPerfil() {
        String nombreJugador = (String) vista.cbJugador.getSelectedItem();
        if (nombreJugador == null || nombreJugador.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un jugador para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar el perfil seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            perfilDAO.eliminarPerfil(nombreJugador);
            JOptionPane.showMessageDialog(vista, "Perfil eliminado correctamente.");
            llenarComboObjetos();
            limpiarCampos();
        }
    }
}