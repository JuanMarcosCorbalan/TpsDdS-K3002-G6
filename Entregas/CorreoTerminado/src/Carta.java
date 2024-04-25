import java.util.ArrayList;

public class Carta extends Envio{

    String tipoCarta;
    Sello tipoSello;

    public Carta(Persona destinatario, Persona remitente, int precio, String codigoRastreo, ArrayList<SucursalVisitada> sucursales, Cartero cartero, String tipoCarta, Sello tipoSello) {
        super(destinatario, remitente, precio, codigoRastreo, sucursales, cartero);
        this.tipoCarta = tipoCarta;
        this.tipoSello = tipoSello;
    }
}
