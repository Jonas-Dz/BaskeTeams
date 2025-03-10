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
    private FrmActualizarJugador vistaActualizarJugador;
    private EstadisticasDAO estadisticasDAO;
    private PerfilDAO perfilDAO;

    public ControladorActualizarJugador(FrmActualizarJugador vista) {
        this.vistaActualizarJugador = vista;
        this.estadisticasDAO = new EstadisticasDAO();
        this.perfilDAO = new PerfilDAO();
        this.vistaActualizarJugador.cbJugador.addActionListener(this);
        this.vistaActualizarJugador.btnGuardar.addActionListener(this);
        this.vistaActualizarJugador.btnRegresar.addActionListener(this);
        this.vistaActualizarJugador.btnEliminar.addActionListener(this); // Add action listener for btnEliminar
        llenarComboObjetos();
    }

    private void llenarComboObjetos() {
        PerfilDAO perfilDAO = new PerfilDAO();  // Crear instancia del DAO
        List<Perfil> perfiles = perfilDAO.obtenerPerfiles(); // Obtener lista de jugadores

        vistaActualizarJugador.cbJugador.removeAllItems(); // Limpiar el ComboBox antes de llenarlo

        for (Perfil perfil : perfiles) {
            vistaActualizarJugador.cbJugador.addItem(perfil.getNombre()); // Agregar los nombres al ComboBox
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaActualizarJugador.btnGuardar) {
            guardarEstadisticas();
        }
        if (e.getSource() == vistaActualizarJugador.btnRegresar) {
            vistaActualizarJugador.dispose();
            FrmPerfiles perfiles = new FrmPerfiles();
            perfiles.setVisible(true);
            perfiles.setLocationRelativeTo(null);
            new ControladorPerfiles(perfiles);
        }
        if (e.getSource() == vistaActualizarJugador.btnEliminar) {
            eliminarPerfil();
        }
    }

    private void limpiarCampos() {
        vistaActualizarJugador.txtRebotesTotales.setText("");
        vistaActualizarJugador.txtBloqueosTotales.setText("");
        vistaActualizarJugador.txtAsistenciasTotales.setText("");
        vistaActualizarJugador.txtPuntosTotales.setText("");
        vistaActualizarJugador.txtRobosTotales.setText("");
        vistaActualizarJugador.txtPartidosJugados.setText("");
        vistaActualizarJugador.txtMVPsTotales.setText("");
    }

    private void guardarEstadisticas() {
        try {
            if (vistaActualizarJugador.txtRebotesTotales.getText().isEmpty() || vistaActualizarJugador.txtBloqueosTotales.getText().isEmpty() ||
                vistaActualizarJugador.txtAsistenciasTotales.getText().isEmpty() || vistaActualizarJugador.txtPuntosTotales.getText().isEmpty() ||
                vistaActualizarJugador.txtRobosTotales.getText().isEmpty() || vistaActualizarJugador.txtPartidosJugados.getText().isEmpty() ||
                vistaActualizarJugador.txtMVPsTotales.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaActualizarJugador, "Todos los campos deben estar llenos.");
                return;
            }

            String nombreJugador = (String) vistaActualizarJugador.cbJugador.getSelectedItem();
            int rebotes = Integer.parseInt(vistaActualizarJugador.txtRebotesTotales.getText());
            int bloqueos = Integer.parseInt(vistaActualizarJugador.txtBloqueosTotales.getText());
            int asistencias = Integer.parseInt(vistaActualizarJugador.txtAsistenciasTotales.getText());
            int puntos = Integer.parseInt(vistaActualizarJugador.txtPuntosTotales.getText());
            int robos = Integer.parseInt(vistaActualizarJugador.txtRobosTotales.getText());
            int partidosJugados = Integer.parseInt(vistaActualizarJugador.txtPartidosJugados.getText());
            int mvps = Integer.parseInt(vistaActualizarJugador.txtMVPsTotales.getText());

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
            JOptionPane.showMessageDialog(vistaActualizarJugador, "Estadísticas actualizadas correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaActualizarJugador, "Solo se admiten números enteros.");
        }
    }

    private void eliminarPerfil() {
        String nombreJugador = (String) vistaActualizarJugador.cbJugador.getSelectedItem();
        if (nombreJugador == null || nombreJugador.isEmpty()) {
            JOptionPane.showMessageDialog(vistaActualizarJugador, "Seleccione un jugador para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vistaActualizarJugador, "¿Está seguro de eliminar el perfil seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            perfilDAO.eliminarPerfil(nombreJugador);
            JOptionPane.showMessageDialog(vistaActualizarJugador, "Perfil eliminado correctamente.");
            llenarComboObjetos();
            limpiarCampos();
        }
    }
}