package auxs;
import java.util.Scanner;

public class Utilidades 
{
    static Scanner sc = new Scanner(System.in);

    public static String leerCampo(String mensaje) 
    {
        System.out.println(mensaje + " (-1 para volver)");
        String valor = sc.nextLine();

        if (valor.equals("-1")) 
        {
            return null;
        }

        return valor;
    }

    public static void pausaMensaje()
    {
        System.out.println("\n\tPresiona Enter para continuar...");
        sc.nextLine();
    }

    public static void pausa()
    {
        sc.nextLine();
    }

    public static void limpiarConsola()
    {
        for (int i = 0; i < 50; i++) 
            System.out.println();
    }

    public static boolean validarSiNo(String respuesta)
    {
        respuesta = respuesta.toUpperCase();

        if(respuesta.equals("SI") || respuesta.equals("NO"))
            return true;
        else 
            return false;

    }

    public static void esperar(int segundos)
    {
        try {
            Thread.sleep(1000*segundos);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

