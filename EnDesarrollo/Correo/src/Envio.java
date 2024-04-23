import java.util.ArrayList;

public class Envio {
    Persona destinatario;
    Persona remitente;
    int precio;
    String codigoRastreo;
    ArrayList<Sucursal> sucursales;
    Cartero cartero;

    public Envio(Persona destinatario, Persona remitente, int precio, String codigoRastreo, ArrayList<Sucursal> sucursales, Cartero cartero) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.precio = precio;
        this.codigoRastreo = codigoRastreo;
        this.sucursales = sucursales;
        this.cartero = cartero;
    }

    public void agregarSucursal(Sucursal sucursalNueva){
        sucursales.add(sucursalNueva);
    }

    public void realizar(){

    }

}
