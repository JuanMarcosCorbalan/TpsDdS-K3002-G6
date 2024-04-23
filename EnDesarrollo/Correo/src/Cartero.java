public class Cartero extends Empleado{
    String puesto;
    public Cartero(String nombre, int dni, int id, String puesto) {
        super(nombre, dni, id);
        this.puesto = puesto;
    }

}
