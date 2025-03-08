package modelo;

public class EstadisticasJugador {
    private int rebotes;
    private int bloqueos;
    private int asistencias;
    private int puntos;
    private int robos;
    private int partidosJugados;
    private int mvps;

    public EstadisticasJugador() {}

    public EstadisticasJugador(int rebotes, int bloqueos, int asistencias, int puntos, int robos, int partidosJugados, int mvps) {
        this.rebotes = rebotes;
        this.bloqueos = bloqueos;
        this.asistencias = asistencias;
        this.puntos = puntos;
        this.robos = robos;
        this.partidosJugados = partidosJugados;
        this.mvps = mvps;
    }

    // Getters y Setters
    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public int getBloqueos() {
        return bloqueos;
    }

    public void setBloqueos(int bloqueos) {
        this.bloqueos = bloqueos;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRobos() {
        return robos;
    }

    public void setRobos(int robos) {
        this.robos = robos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getMvps() {
        return mvps;
    }

    public void setMvps(int mvps) {
        this.mvps = mvps;
    }

    @Override
    public String toString() {
        return "EstadisticasJugador{" +
                "rebotes=" + rebotes +
                ", bloqueos=" + bloqueos +
                ", asistencias=" + asistencias +
                ", puntos=" + puntos +
                ", robos=" + robos +
                ", partidosJugados=" + partidosJugados +
                ", mvps=" + mvps +
                '}';
    }
}