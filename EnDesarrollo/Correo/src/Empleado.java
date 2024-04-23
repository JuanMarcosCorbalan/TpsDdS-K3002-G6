public class Empleado  {
    String nombre;
    int dni;
    int id;
    Sucursal sucursal;

    public Empleado(String nombre, int dni, int id, Sucursal sucursal) {
        this.nombre = nombre;
        this.dni = dni;
        this.id = id;
        this.sucursal = sucursal;
    }
}
