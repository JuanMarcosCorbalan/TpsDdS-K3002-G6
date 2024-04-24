import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.*;

public class Main {
    //PERSONAS DEFAULT
    private static Persona destinatario = new Persona("Juan");
    private static Persona remitente = new Persona("Pedro");
    private static Cartero cartero = new Cartero("Nombre", 25689485, 4, "C");
    //SUCURSALES
    private static Sucursal central = new Sucursal("Sucursal Pilar",1,"Pilar");
    private static Sucursal moreno = new Sucursal("Sucursal Moreno",2,"Moreno");
    private static Sucursal retiro = new Sucursal("Sucursal Retiro",3,"CABA");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //LISTA ENVIOS, SE ANIADE UN ENVIO CADA VEZ QUE SE CREA
        ArrayList<Envio> listaEnvios = new ArrayList<>();

        //ACA IRIA LA INTERFAZ DE USUARIO DEPENDIENDO DE LO QUE QUIERA HACER, SE PUEDE CON SCANNER

        // IMPLEMENTACION DE CONSOLA
        while (true) {
            System.out.println("Ingresa el nombre de la clase que deseas instanciar (o 'salir' para salir):");
            String className = scanner.nextLine();

            if (className.equalsIgnoreCase("salir")) {
                break;
            }

            try {
                Class<?> cls = Class.forName(className);

                // Obtener constructores de la clase
                Constructor<?>[] constructors = cls.getDeclaredConstructors();
                if (constructors.length == 0) {
                    System.out.println("La clase no tiene constructores accesibles.");
                    continue;
                }

                // Mostrar los constructores de la clase
                System.out.println("Selecciona un constructor:");
                for (int i = 0; i < constructors.length; i++) {
                    System.out.println(i + ": " + constructors[i].toString());
                }

                // Obtener el índice del constructor seleccionado
                int constructorIndex = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                // Obtener los parámetros del constructor seleccionado
                Parameter[] parameters = constructors[constructorIndex].getParameters();
                Object[] parameterValues = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    System.out.println("Ingresa el valor para el parámetro " + parameters[i].getName() + " de tipo " + parameters[i].getType().getSimpleName() + ":");
                    String paramValue = scanner.nextLine();
                    parameterValues[i] = convertToType(paramValue, parameters[i].getType());
                }

                // Instanciar la clase con los parámetros proporcionados
                Object obj = constructors[constructorIndex].newInstance(parameterValues);

                // Imprimir la instancia creada
                System.out.println("Instancia creada: " + obj.toString());

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                     | InvocationTargetException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();


        //Se podria manejar todos los errores aca y no dejar entrar a la funcion si ingresa un codigo invalido

        //se le deberia pedir al usuario tanto el precio como el codigo rastreo a la hora de crear el envio
        //el codigo rastreo se podria generar automaticamente
        int precio = 2500;
        String codigoRastreo = "2403";
        //para las consultas sobre un envio ya hecho se pediria codigoRastreo nada mas

        realizarEnvio(listaEnvios,precio,codigoRastreo);//creo un nuevo envio con precio y codigoRastreo
        //aniado 2 sucursales
        aniadirSucursal(listaEnvios,codigoRastreo,moreno,15,"25/03");
        aniadirSucursal(listaEnvios,codigoRastreo,retiro,15,"26/03");
        //busco sucursal actual, la ultima en la que estuvo el paquete
        buscarSucursalActual(listaEnvios,codigoRastreo);
        //muestro todas las sucursales con hora y fecha en orden
        mostrarCamino(listaEnvios,codigoRastreo);
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

    // Método para convertir el valor de entrada a un tipo específico
    private static Object convertToType(String value, Class<?> type) {
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (type.equals(String.class)) {
            return value;
        }
        // Se pueden agregar mas tipos si necesitamos
        return null;
    }


    }
