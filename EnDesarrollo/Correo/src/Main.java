import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;


public class Main {
    //PERSONAS DEFAULT
    private static Persona destinatario = new Persona("Juan");

    private static Persona remitente = new Persona("Pedro");
    private static Cartero cartero = new Cartero("Nombre", 25689485, 4, "C");
    //SUCURSALES
    private static Sucursal central = new Sucursal("Sucursal Pilar",1,"Pilar");
    private static Sucursal moreno = new Sucursal("Sucursal Moreno",2,"Moreno");
    private static Sucursal retiro = new Sucursal("Sucursal Retiro",3,"CABA");

    private static Map<String, Object> objetos = new HashMap<>();


    public static void main(String[] args) {
//        int precio = 2500;
//        String codigoRastreo = "2403";

        Scanner scanner = new Scanner(System.in);
        ArrayList<SucursalVisitada> sucursales = new ArrayList<>();

        // creo que el envio es uno solo, no se si tiene mucho sentido que se puedan hacer varios envios
        //Envio nuevoEnvio = new Envio(destinatario,remitente,precio,codigoRastreo,sucursales,cartero);


        //LISTA ENVIOS, SE ANIADE UN ENVIO CADA VEZ QUE SE CREA
        ArrayList<Envio> listaEnvios = new ArrayList<>();

        //ACA IRIA LA INTERFAZ DE USUARIO DEPENDIENDO DE LO QUE QUIERA HACER, SE PUEDE CON SCANNER
        // IMPLEMENTACION DE CONSOLA
        System.out.println("Ingrese el precio del envio");
        int precio  = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el codigo de seguimiento");
        String codigoRastreo  = scanner.nextLine();



        //Se podria manejar todos los errores aca y no dejar entrar a la funcion si ingresa un codigo invalido

        //se le deberia pedir al usuario tanto el precio como el codigo rastreo a la hora de crear el envio
        //el codigo rastreo se podria generar automaticamente

        //para las consultas sobre un envio ya hecho se pediria codigoRastreo nada mas

        realizarEnvio(listaEnvios,precio,codigoRastreo);//creo un nuevo envio con precio y codigoRastreo
        //aniado 2 sucursales
        aniadirSucursal(listaEnvios,codigoRastreo,moreno,15,"25/03");
        aniadirSucursal(listaEnvios,codigoRastreo,retiro,15,"26/03");

        while(true){
            System.out.println("Ingrese el numero de operacion a realizar");
            System.out.println("1 para mostrar la ubicacion actual");
            System.out.println("2 para mostrar el recorrido completo");
            System.out.println("0 para salir");

            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 0) {
                break;
            }
            if (opcion == 1) {
                //busco sucursal actual, la ultima en la que estuvo el paquete
                buscarSucursalActual(listaEnvios,codigoRastreo);
            }
            if (opcion == 2) {
                //muestro todas las sucursales con hora y fecha en orden
                mostrarCamino(listaEnvios,codigoRastreo);
            }
        }

        scanner.close();


    }
    public static void mostrarCamino(ArrayList<Envio> listaEnvios, String codigoRastreo)
    {
        //FALTA MANEJO DE QUE PASA SI EL ENVIO NO EXISTE, buscarIndiceSucursal devuelve -1
        int indice = buscarIndiceSucursal(listaEnvios,codigoRastreo);

        listaEnvios.get(indice).mostrarSucursalesVisitadas();
    }

    public static void aniadirSucursal(ArrayList<Envio> listaEnvios, String codigoRastreo, Sucursal sucursal,int hora,String fecha)
    {
        //FALTA MANEJO DE QUE PASA SI EL ENVIO NO EXISTE, buscarIndiceSucursal devuelve -1
        int indice = buscarIndiceSucursal(listaEnvios,codigoRastreo);
        //Creo envio auxiliar, la sucursal visitada y la aniado al envio
        Envio envioBuscado = listaEnvios.get(indice);
        SucursalVisitada nuevaSucursal = new SucursalVisitada(envioBuscado,sucursal,hora,fecha);
        envioBuscado.agregarSucursal(nuevaSucursal);
    }
    public static void realizarEnvio(ArrayList<Envio> listaEnvios,int precio,String codigoRastreo) {
        ArrayList<SucursalVisitada> sucursales = new ArrayList<>();
        Envio nuevoEnvio = new Envio(destinatario,remitente,precio,codigoRastreo,sucursales,cartero);
        //SIEMPRE INICIA SU RECORRIDO EN CENTRAL, HORA HARDCODEADA, se podria hacer con LocalTime y LocalDate
        SucursalVisitada centralInicio = new SucursalVisitada(nuevoEnvio,central,14,"24/03");
        nuevoEnvio.agregarSucursal(centralInicio);
        listaEnvios.add(nuevoEnvio);
    }
    public static void buscarSucursalActual(ArrayList<Envio> listaEnvios, String codigoRastreo)
    {
        //devuelve -1 en caso de error, falta manejarlo
        int indice = buscarIndiceSucursal(listaEnvios,codigoRastreo);

        int posicionUltimaSucursal = listaEnvios.get(indice).sucursales.size() - 1;
        System.out.println("Su envio se encuentra en:");
        System.out.println(listaEnvios.get(indice).sucursales.get(posicionUltimaSucursal).sucursalActual.nombreSucursal);

    }
    public static int buscarIndiceSucursal(ArrayList<Envio> listaEnvios, String codigoRastreo)
    {
        int encontrado = 0;
        int indice;
        for(indice=0;indice<listaEnvios.size();indice++)
        {
            if(listaEnvios.get(indice).codigoRastreo.equals(codigoRastreo))
            {
                encontrado = 1;
                break;
            }
            indice++;
        }
        if(encontrado==0)
        {
            return -1;
        }
        else {
            return indice;
        }
    }


//    private static void llamarMetodo(Scanner scanner) {
//        System.out.println("Ingresa el nombre del objeto:");
//        String objetoNombre = scanner.nextLine();
//        Object objeto = objetos.get(objetoNombre);
//        if (objeto == null) {
//            System.out.println("Objeto no encontrado.");
//            return;
//        }
//
//        System.out.println("Ingresa el nombre del método que desea llamar:");
//        String methodName = scanner.nextLine();
//
//        // Invocar al método correspondiente en el objeto encontrado
//        try {
//            objeto.getClass().getMethod(methodName).invoke(objeto);
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
    }
