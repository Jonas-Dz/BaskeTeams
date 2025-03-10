package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.FrmSimular;
import vista.FrmPartido;

public class ControladorSimular implements ActionListener {

    private FrmSimular vista;

    public ControladorSimular(FrmSimular vista) {
        this.vista = vista;

        // Registrar eventos de botones
        this.vista.btnSimular.addActionListener(this); // Botón "Simular"
        this.vista.btnMenu.addActionListener(this); // Botón "Menu"
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnSimular) {
            simularPartido();
        }

        if (e.getSource() == vista.btnMenu) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de partidos (FrmPartido)
            FrmPartido frmPartido = new FrmPartido();
            frmPartido.setVisible(true);
            frmPartido.setLocationRelativeTo(null);

            // Pasar el control al ControladorPartido
            new ControladorPartido(frmPartido);
        }
    }

    private void simularPartido() {
        String alineacion1 = (String) vista.alineacion1.getSelectedItem();
        String alineacion2 = (String) vista.alineacion2.getSelectedItem();

        if (alineacion1.isEmpty() || alineacion2.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar los nombres de las dos alineaciones.");
            return;
        }

        // Simular los puntos de cada alineación
        int puntosAlineacion1 = generarPuntosAlineacion();
        int puntosAlineacion2 = generarPuntosAlineacion();

        // Determinar el resultado
        String resultado;
        int diferencia = Math.abs(puntosAlineacion1 - puntosAlineacion2);

        if (puntosAlineacion1 > puntosAlineacion2) {
            resultado = "¡La alineación " + alineacion1 + " ha ganado el partido!\n"
                      + "Puntos: " + alineacion1 + " (" + puntosAlineacion1 + ") vs " + alineacion2 + " (" + puntosAlineacion2 + ")\n"
                      + "Diferencia: " + diferencia + " puntos.";
        } else if (puntosAlineacion2 > puntosAlineacion1) {
            resultado = "¡La alineación " + alineacion2 + " ha ganado el partido!\n"
                      + "Puntos: " + alineacion2 + " (" + puntosAlineacion2 + ") vs " + alineacion1 + " (" + puntosAlineacion1 + ")\n"
                      + "Diferencia: " + diferencia + " puntos.";
        } else {
            resultado = "El partido ha terminado en empate.\n"
                      + "Puntos: " + alineacion1 + " (" + puntosAlineacion1 + ") vs " + alineacion2 + " (" + puntosAlineacion2 + ")";
        }

        // Mostrar el resultado
        JOptionPane.showMessageDialog(vista, resultado);
    }

    private int generarPuntosAlineacion() {
        // Generar puntos aleatorios entre 70 y 120 para simular un partido realista
        return (int) (Math.random() * 50) + 70;
    }
}