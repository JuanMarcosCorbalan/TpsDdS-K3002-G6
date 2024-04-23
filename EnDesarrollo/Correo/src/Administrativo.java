public class Administrativo extends Empleado {
    String categoria;

    public Administrativo(String nombre, int dni, int id, Sucursal sucursal, String categoria) {
        super(nombre, dni, id, sucursal);
        this.categoria = categoria;
    }
}
