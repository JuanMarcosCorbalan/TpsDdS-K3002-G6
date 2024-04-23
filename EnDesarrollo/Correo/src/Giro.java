import java.util.ArrayList;

public class Giro extends Envio{

    int importe;

    public Giro(Persona destinatario, Persona remitente, int precio, String codigoRastreo, ArrayList<Sucursal> sucursales, Cartero cartero, int importe) {
        super(destinatario, remitente, precio, codigoRastreo, sucursales, cartero);
        this.importe = importe;
    }
}
