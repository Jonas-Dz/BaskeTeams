package controlador;
//prueba commit
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.AlineacionDAO;
import modelo.Perfil;
import modelo.PerfilDAO;
import vista.FrmActualizarJugador;
import vista.FrmAlineacion;
import vista.FrmCompararAlineaciones;
import vista.FrmCompararJugador;
import vista.FrmModificarAlineacion;
import vista.FrmNuevoA;
import vista.FrmNuevoPerfil;
import vista.FrmPartido;
import vista.FrmPerfiles;
import vista.FrmPrincipal;
import vista.FrmReporte;
import vista.FrmSimular;
import vista.FrmSugerir;

public class ControladorPrincipal implements ActionListener {

    Perfil objPerfil = new Perfil();
    PerfilDAO pDAO = new PerfilDAO();
    AlineacionDAO aDAO = new AlineacionDAO();
    FrmPrincipal vistaPrincipal = new FrmPrincipal();
    FrmPerfiles vistaPerfil = new FrmPerfiles();
    FrmNuevoPerfil nPerfil = new FrmNuevoPerfil();
    FrmNuevoA vistaNuevoA = new FrmNuevoA();
    FrmAlineacion vistaAlineacion = new FrmAlineacion();
    FrmPartido vistaPartido = new FrmPartido();

    //Jonathan
    FrmActualizarJugador vistaActualizarJugador = new FrmActualizarJugador();
    FrmCompararAlineaciones vistaCompararAlineaciones = new FrmCompararAlineaciones();
    FrmCompararJugador vistaCompararJugador = new FrmCompararJugador();
    FrmModificarAlineacion vistaModificarAlineacion = new FrmModificarAlineacion();

    //Stalyn
    FrmReporte vistaReporte = new FrmReporte();
    FrmSimular vistaSimular = new FrmSimular();
    FrmSugerir vistaSugerir = new FrmSugerir();

    public ControladorPrincipal() {
    }

    public ControladorPrincipal(FrmPrincipal vista) {
        vistaPrincipal = vista;
        //Ventana principal
        vistaPrincipal.btnPerfiles.addActionListener(this);
        vistaPrincipal.btnAlineaciones.addActionListener(this);
        vistaPrincipal.btnPartidos.addActionListener(this);
    }

    public ControladorPrincipal(FrmPerfiles vista2) {
        vistaPerfil = vista2;
        //Ventana perfiles
        vistaPerfil.btnNuevoP.addActionListener(this);
        vistaPerfil.btnActualizarP.addActionListener(this);
        vistaPerfil.btnCompararP.addActionListener(this);
        vistaPerfil.btnMenu.addActionListener(this);
    }

    public ControladorPrincipal(FrmNuevoPerfil vista3, PerfilDAO dao) {
        nPerfil = vista3;
        pDAO = dao;
        //Ventana nuevo perfil
        nPerfil.btnSiguiente.addActionListener(this);
        nPerfil.btnAtras.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //SECCION PERFILES
        if (e.getSource() == vistaPrincipal.btnPerfiles) {
            vistaPerfil.setVisible(true);
            vistaPerfil.setLocationRelativeTo(null);
        }
        //SECCION NUEVO PERFIL
        if (e.getSource() == vistaPerfil.btnNuevoP) {
            nPerfil.setVisible(true);
            nPerfil.setLocationRelativeTo(nPerfil);
        }
        //SECCION CREAR PERFIL
        if (e.getSource() == nPerfil.btnSiguiente) {
            try {
                String nombre = nPerfil.txtNombre.getText();
                String posicion = nPerfil.comboPos.getSelectedItem().toString();
                Float altura = Float.parseFloat(nPerfil.txtAltura.getText());
                Float peso = Float.parseFloat(nPerfil.txtPeso.getText());
                int edad = Integer.parseInt(nPerfil.txtEdad.getText());

                // Validar altura
                if (altura < 1.50 || altura > 2.10) {
                    nPerfil.txtAltura.setText("");
                    throw new IllegalArgumentException("Estatura debe estar entre 1.50m y 2.10m.");
                }
                // Validar peso
                if (peso <= 30.0) {
                    nPerfil.txtPeso.setText("");
                    throw new IllegalArgumentException("El peso debe ser mayor que 30 kg.");
                }

                // Crear Perfil
                Perfil perf = new Perfil(nombre, posicion, altura, peso, edad);
                // Añadir Perfil a la base de datos
                pDAO.crearPerfil(perf);
                System.out.println("Perfil creado correctamente"); // Mensaje de depuración
                // Mensaje de aceptación
                JOptionPane.showMessageDialog(nPerfil, "Perfil creado correctamente.");
                // Limpiar campos
                limpiarCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(nPerfil, "Solo puede ingresar números en peso, altura y edad.");
            }
        }
        if (e.getSource() == nPerfil.btnAtras) {
            nPerfil.dispose();
        }

        // Sección Alineaciones: cargar jugadores por posición
        if (e.getSource() == vistaNuevoA.comboBase) {
            cargarJugadoresPorPosicion();
        }
    }

    private void limpiarCampos() {
        nPerfil.txtNombre.setText("");
        nPerfil.comboPos.setSelectedIndex(0);
        nPerfil.txtAltura.setText("");
        nPerfil.txtPeso.setText("");
        nPerfil.txtEdad.setText("");
    }

    private void cargarJugadoresPorPosicion() {
        String posicionSeleccionada = vistaNuevoA.comboBase.getSelectedItem().toString();

        java.util.List<Perfil> jugadores = aDAO.obtenerJugadoresPorPosicion(posicionSeleccionada);

        vistaNuevoA.comboBase.removeAllItems();

        for (Perfil jugador : jugadores) {
            vistaNuevoA.comboBase.addItem(jugador.getNombre()); // Asumimos que quieres mostrar el nombre
        }
    }
}
