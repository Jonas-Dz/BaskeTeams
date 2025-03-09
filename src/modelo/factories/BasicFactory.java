    package modelo.factories;

    import modelo.alineaciones.Alineacion;
    import modelo.perfiles.Perfil;

    public class BasicFactory implements AbstractFactory {

        @Override
        public Perfil crearJugador(String nombre, String posicion, float altura, float peso, int edad) {
            return new Perfil(nombre, posicion, altura, peso, edad);
        }

        @Override
        public Alineacion crearAlineacion(String nombre) {
            return new Alineacion(nombre);
        }
    }