public class SucursalVisitada {
    Envio envioCorrespondiente;
    Sucursal sucursalActual;
    int hora;
    String fecha;

    public SucursalVisitada(Envio envioCorrespondiente, Sucursal sucursalActual, int hora, String fecha)
    {
        this.envioCorrespondiente = envioCorrespondiente;
        this.sucursalActual = sucursalActual;
        this.hora = hora;
        this.fecha = fecha;
    }
}
