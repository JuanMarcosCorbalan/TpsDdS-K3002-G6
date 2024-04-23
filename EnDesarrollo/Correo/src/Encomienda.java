import java.util.ArrayList;

public class Encomienda extends Envio {
    String tipoEmpaquetado;

    public Encomienda(Persona destinatario, Persona remitente, int precio, String codigoRastreo, ArrayList<SucursalVisitada> sucursales, Cartero cartero, String tipoEmpaquetado) {
        super(destinatario, remitente, precio, codigoRastreo, sucursales, cartero);
        this.tipoEmpaquetado = tipoEmpaquetado;
    }
}

