public class Administrativo extends Empleado {
    String categoria;

    public Administrativo(String nombre, int dni, int id,String categoria) {
        super(nombre, dni, id);
        this.categoria = categoria;
    }
}
