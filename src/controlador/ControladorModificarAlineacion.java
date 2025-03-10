/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.alineaciones.Alineacion;
import modelo.alineaciones.AlineacionDAO;
import modelo.perfiles.Perfil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.alineaciones.Componente;
import vista.FrmAlineacion;
import vista.FrmModificarA;

/**
 *
 * @author User
 */
public class ControladorModificarAlineacion implements ActionListener{

    private FrmModificarA vista;
    private AlineacionDAO alineacionDAO;

    public ControladorModificarAlineacion(FrmModificarA modificarAlineacion) {
        this.vista = modificarAlineacion;
        this.alineacionDAO = new AlineacionDAO();
        this.vista.btnBuscarAlineaciones.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
        this.vista.btnSiguiente.addActionListener(this);
        this.vista.cbAPivot.addActionListener(this);
        this.vista.cbAlero.addActionListener(this);
        this.vista.cbBase.addActionListener(this);
        this.vista.cbEscolta.addActionListener(this);
        this.vista.cbPivot.addActionListener(this);
        this.vista.comboAlineaciones.addActionListener(this);
        inicializarCombobox();
        cargarAlineaciones();
    }

    private void inicializarCombobox() {
        vista.cbBase.removeAllItems();
        vista.cbEscolta.removeAllItems();
        vista.cbAlero.removeAllItems();
        vista.cbAPivot.removeAllItems();
        vista.cbPivot.removeAllItems();
    }

    private void cargarAlineaciones() {
        List<Alineacion> alineaciones = alineacionDAO.obtenerTodasAlineaciones();
        for (Alineacion alineacion : alineaciones) {
            vista.comboAlineaciones.addItem(alineacion.getNombre());
        }
    }

    private void buscarAlineacion() {
        String nombreAlineacion = (String) vista.comboAlineaciones.getSelectedItem();
        Alineacion alineacion = alineacionDAO.obtenerAlineacionPorNombre(nombreAlineacion);

        if (alineacion != null) {
            // Clear existing items
            inicializarCombobox();

            for (Componente componente : alineacion.getComponentes()) {
                if (componente instanceof Perfil) {
                    Perfil perfil = (Perfil) componente;
                    switch (perfil.getPosicion()) {
                        case "BASE":
                            vista.cbBase.addItem(perfil.getNombre());
                            break;
                        case "ESCOLTA":
                            vista.cbEscolta.addItem(perfil.getNombre());
                            break;
                        case "ALERO":
                            vista.cbAlero.addItem(perfil.getNombre());
                            break;
                        case "ALA PIVOT":
                            vista.cbAPivot.addItem(perfil.getNombre());
                            break;
                        case "PIVOT":
                            vista.cbPivot.addItem(perfil.getNombre());
                            break;
                    }
                }
            }
        }
    }
    
    private void modificarAlineacion() {
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea modificar la alineación seleccionada?", "Confirmar modificación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String nombreAlineacion = (String) vista.comboAlineaciones.getSelectedItem();
            Alineacion alineacion = new Alineacion(nombreAlineacion);

            for (int i = 0; i < vista.cbBase.getItemCount(); i++) {
                alineacion.agregarComponente(new Perfil((String) vista.cbBase.getItemAt(i), "Base", 0f, 0f, 0));
            }
            for (int i = 0; i < vista.cbEscolta.getItemCount(); i++) {
                alineacion.agregarComponente(new Perfil((String) vista.cbEscolta.getItemAt(i), "Escolta", 0f, 0f, 0));
            }
            for (int i = 0; i < vista.cbAlero.getItemCount(); i++) {
                alineacion.agregarComponente(new Perfil((String) vista.cbAlero.getItemAt(i), "Alero", 0f, 0f, 0));
            }
            for (int i = 0; i < vista.cbAPivot.getItemCount(); i++) {
                alineacion.agregarComponente(new Perfil((String) vista.cbAPivot.getItemAt(i), "Ala - pivot", 0f, 0f, 0));
            }
            for (int i = 0; i < vista.cbPivot.getItemCount(); i++) {
                alineacion.agregarComponente(new Perfil((String) vista.cbPivot.getItemAt(i), "Pivot", 0f, 0f, 0));
            }

            alineacionDAO.crearAlineacion(alineacion);
            JOptionPane.showMessageDialog(vista, "Alineación modificada exitosamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //REGRESAR
        if(e.getSource() == vista.btnAtras){
            vista.dispose();
            FrmAlineacion alineaciones = new FrmAlineacion();
            alineaciones.setVisible(true);
            alineaciones.setLocationRelativeTo(null);
            new ControladorAlineacion(alineaciones);
        }
        //BUSCAR ALINEACION
        if(e.getSource() == vista.btnBuscarAlineaciones){
            buscarAlineacion();
        }
        //MODIFICAR ALINEACION
        if(e.getSource() == vista.btnSiguiente){
            modificarAlineacion();
        }
    }
}