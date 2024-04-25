import java.util.ArrayList;

public class Envio {
    Persona destinatario;
    Persona remitente;
    int precio;
    String codigoRastreo;
    Cartero cartero;
    ArrayList<SucursalVisitada> sucursales;

    public Envio(Persona destinatario, Persona remitente, int precio, String codigoRastreo, ArrayList<SucursalVisitada> sucursales, Cartero cartero) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.precio = precio;
        this.codigoRastreo = codigoRastreo;
        this.sucursales = sucursales;
        this.cartero = cartero;

    }

    public void agregarSucursal(SucursalVisitada sucursalNueva){
        sucursales.add(sucursalNueva);
    }
    public void mostrarSucursalesVisitadas()
    {
        for(int indice=0;indice<sucursales.size();indice++)
        {
            System.out.println("Sucursal: " + sucursales.get(indice).sucursalActual.nombreSucursal);
            System.out.println("Fecha: " + sucursales.get(indice).fecha);
            System.out.println("Hora: " + sucursales.get(indice).hora);
        }
    }

    public void realizar(){

    }

}
