public class Cartero extends Empleado{
    Envio envioActual;

    public Cartero(String nombre, int dni, int id, Sucursal sucursal, Envio envioActual) {
        super(nombre, dni, id, sucursal);
        this.envioActual = envioActual;
    }
}
