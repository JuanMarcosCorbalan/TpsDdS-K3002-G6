import java.util.ArrayList;

public class Telegrama extends Envio{

    String texto;
    String tipoTelegrama;

    public Telegrama(Persona destinatario, Persona remitente, int precio, String codigoRastreo, ArrayList<Sucursal> sucursales, Cartero cartero, String texto, String tipoTelegrama) {
        super(destinatario, remitente, precio, codigoRastreo, sucursales, cartero);
        this.texto = texto;
        this.tipoTelegrama = tipoTelegrama;
    }
}
