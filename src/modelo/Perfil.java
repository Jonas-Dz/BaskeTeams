package modelo;
/**
 *
 * @author danip
 */
public class Perfil {
    private String nombre;
    private String posicion;
    private float altura;
    private float peso;
    private int edad;
    private int rebotes, bloqueos, asistencias, puntos, robos, partjugados, mvps;

    public Perfil() {
    }

    public Perfil(String nombre, String posicion, float altura, float peso, int edad) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
    }

    public Perfil(String nombre, String posicion, float altura, float peso, int edad, int rebotes, int bloqueos, int asistencias, int puntos, int robos, int partjugados, int mvps) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
        this.rebotes = rebotes;
        this.bloqueos = bloqueos;
        this.asistencias = asistencias;
        this.puntos = puntos;
        this.robos = robos;
        this.partjugados = partjugados;
        this.mvps = mvps;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public float getAltura() {
        return altura;
    }

    public float getPeso() {
        return peso;
    }

    public int getEdad() {
        return edad;
    }

    public int getRebotes() {
        return rebotes;
    }

    public int getBloqueos() {
        return bloqueos;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getRobos() {
        return robos;
    }

    public int getPartjugados() {
        return partjugados;
    }

    public int getMvps() {
        return mvps;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public void setBloqueos(int bloqueos) {
        this.bloqueos = bloqueos;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setRobos(int robos) {
        this.robos = robos;
    }

    public void setPartjugados(int partjugados) {
        this.partjugados = partjugados;
    }

    public void setMvps(int mvps) {
        this.mvps = mvps;
    }

    @Override
    public String toString() {
        return "Perfil{" + "nombre=" + nombre + ", posicion=" + posicion + ", altura=" + altura + ", peso=" + peso + ", edad=" + edad + ", rebotes=" + rebotes + ", bloqueos=" + bloqueos + ", asistencias=" + asistencias + ", puntos=" + puntos + ", robos=" + robos + ", partjugados=" + partjugados + ", mvps=" + mvps + '}';
    }
}