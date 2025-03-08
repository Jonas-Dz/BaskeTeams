package modelo;

public class Perfil {
    private String nombre;
    private String posicion;
    private float altura;
    private float peso;
    private int edad;
    private EstadisticasJugador estadisticas; 

    public Perfil() {}

    public Perfil(String nombre, String posicion, float altura, float peso, int edad) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
    }

    public Perfil(String nombre, String posicion, float altura, float peso, int edad, EstadisticasJugador estadisticas) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
        this.estadisticas = estadisticas;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public EstadisticasJugador getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(EstadisticasJugador estadisticas) {
        this.estadisticas = estadisticas;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", edad=" + edad +
                ", estadisticas=" + estadisticas +
                '}';
    }
}