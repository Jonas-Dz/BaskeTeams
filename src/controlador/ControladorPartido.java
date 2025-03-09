package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FrmPartido;
import vista.FrmSimular;
import vista.FrmReporte;

public class ControladorPartido implements ActionListener {

    private FrmPartido vista;

    public ControladorPartido(FrmPartido vista) {
        this.vista = vista;

        // Registrar eventos de botones
        this.vista.btnSimulacionPt.addActionListener(this);
        this.vista.btnReportePt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnSimulacionPt) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de simulación de partido
            FrmSimular frmSimular = new FrmSimular();
            frmSimular.setVisible(true);
            frmSimular.setLocationRelativeTo(null);

            // Pasar el control al ControladorSimular (si existe)
            // new ControladorSimular(frmSimular);
        }

        if (e.getSource() == vista.btnReportePt) {
            // Cerrar la ventana actual
            vista.dispose();

            // Abrir la ventana de reporte de partido
            FrmReporte frmReporte = new FrmReporte();
            frmReporte.setVisible(true);
            frmReporte.setLocationRelativeTo(null);

            // Pasar el control al ControladorReporte (si existe)
            // new ControladorReporte(frmReporte);
        }
    }
}