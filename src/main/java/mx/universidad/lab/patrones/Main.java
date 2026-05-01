import factory.NotificationFactory;
import singleton.NotificationLogger;
import thread.NotificationWorker;
import java.util.Scanner;
import auxs.Utilidades;

public class Main {
    
    static NotificationFactory factory = new NotificationFactory();
    static NotificationLogger logger = NotificationLogger.getInstance();
    
    static Scanner sc = new Scanner(System.in);

    static Thread[] threads = new Thread[5];
    static int count = 0;

    public static void main(String[] args) throws InterruptedException{

        String respuesta;
        int opcion = 0;

        do{
            Utilidades.limpiarConsola();
            System.out.println("\n\t\tNOTIFICATION MANAGER");
            System.out.println("\n\t1. Redactar mensaje\n\t2. Enviar mensajes\n\t3. Salir");
            System.out.print("\n\t>>");
            respuesta = sc.nextLine();
            opcion = Integer.parseInt(respuesta);



            switch(opcion)
            {
                case 1:
                    redactMessage();
                    break;
                case 2:
                    sendMessages();
                    Utilidades.pausaMensaje();
                    break;
            }

        }while(opcion != 3);

    }

    public static void redactMessage(){

        Utilidades.limpiarConsola();
        if(count >= 5){
            System.out.println("\n\tLimite de mensajes alcanzado");
            return;
        }

        System.out.print("\n\t\tELIGE EL TIPO DE MENSAJE");
        System.out.println("\n\t1. Email\n\t2. SMS\n\t3. Push\n\t4. Salir");
        System.out.print("\n\t>>");
        String respuesta = sc.nextLine();

        int opcion = Integer.parseInt(respuesta);
        String tipo = "";

        switch(opcion)
        {
            case 1:
                tipo = "EMAIL";
                break;
            case 2:
                tipo = "SMS";
                break;
            case 3:
                tipo = "PUSH";
                break;
        }

        String mensaje = readMessage();
        String destinatario = readAddressee();

        threads[count] = new Thread (new NotificationWorker(tipo, destinatario, mensaje));
        count++;

        System.out.print("\n\tMensaje guardado");
        Utilidades.pausaMensaje();

    }

    public static void sendMessages() throws InterruptedException{
        
        Utilidades.limpiarConsola();

        for(int i = 0; i < count; i++){
            threads[i].start();
            
            for(int j = 0; j < 3; j++)
            {
                System.out.print("\t.");
                Utilidades.esperar(1);
            }
        }

        for(int i = 0; i < count; i++){
            threads[i].join();
        }

        NotificationLogger.getInstance().getLogs().forEach(System.out::println);

    }


    public static String readMessage()
    {

        String mensaje;

        System.out.print("\n\tIntroduce el mensaje: \n>>");
        mensaje = sc.nextLine();

        return mensaje;
    
    }

    public static String readAddressee(){

        String addressee;

        System.out.print("\n\tIntroduce el destinatario: \n>>");
        addressee = sc.nextLine();

        return addressee;
    }


}

