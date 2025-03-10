package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FrmSimular;

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
            // Lógica para simular el partido
            System.out.println("Simulando partido...");
            // Aquí puedes agregar la lógica para simular el partido
        }

        if (e.getSource() == vista.btnMenu) {
            // Cerrar la ventana actual
            vista.dispose();
        }
    }
}
