package modelo.reportes;

public class GestorReportes {

    private static GestorReportes instancia;

    private GestorReportes() {
        // Inicialización del gestor de reportes
    }

    public static GestorReportes getInstancia() {
        if (instancia == null) {
            instancia = new GestorReportes();
        }
        return instancia;
    }

    public void generarReporte(String tipoReporte) {
        System.out.println("Generando reporte de tipo: " + tipoReporte);
        // Lógica para generar el reporte
    }
}