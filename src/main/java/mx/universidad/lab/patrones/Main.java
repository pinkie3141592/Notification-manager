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

    public static void main(String[] args) throws InterruptedException {

        int opcion = 0;

        do{
            Utilidades.limpiarConsola();
            System.out.println("\n\t\tNOTIFICATION MANAGER");
            System.out.println("\n\t1. Redactar mensaje\n\t2. Enviar mensajes\n\t3. Salir");
            System.out.print("\n\t>>");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("\n\tEntrada inválida");
                Utilidades.pausaMensaje();
                continue;
            }

            switch(opcion){
                case 1:
                    redactMessage();
                    break;
                case 2:
                    sendMessages();
                    Utilidades.pausaMensaje();
                    break;
                case 3:
                    System.out.println("\n\tSaliendo...");
                    break;
                default:
                    System.out.println("\n\tOpción inválida");
                    Utilidades.pausaMensaje();
            }

        }while(opcion != 3);
    }


    public static void redactMessage(){

        Utilidades.limpiarConsola();

        if(count >= 5){
            System.out.println("\n\tLimite de mensajes alcanzado");
            Utilidades.pausaMensaje();
            return;
        }

        int opcion = 0;
        String tipo = "";

        do{
            System.out.println("\n\t1. Email\n\t2. SMS\n\t3. Push\n\t4. Cancelar");
            System.out.print("\n\t>>");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("\n\tEntrada inválida");
                continue;
            }

            switch(opcion){
                case 1: tipo = "EMAIL"; break;
                case 2: tipo = "SMS"; break;
                case 3: tipo = "PUSH"; break;
                case 4: return;
                default: System.out.println("\n\tOpción inválida");
            }

        } while(opcion < 1 || opcion > 4);

        String mensaje = readMessage();
        String destinatario = readAddressee();

        threads[count] = new Thread(
            new NotificationWorker(tipo, destinatario, mensaje)
        );
        count++;

        System.out.println("\n\tMensaje guardado");
        Utilidades.pausaMensaje();
    }


    public static void sendMessages() throws InterruptedException {

        Utilidades.limpiarConsola();

        if(count == 0){
            System.out.println("\n\tNo hay mensajes para enviar");
            return;
        }

        for(int i = 0; i < count; i++){
            threads[i].start();
            for(int j = 0; j < 3; j++){
                System.out.println(".");
                Utilidades.esperar(1);
            }
        }

        for(int i = 0; i < count; i++){
            threads[i].join();
        }

        System.out.println("\n\n\t=== LOGS ===");
        NotificationLogger.getInstance().getLogs().forEach(System.out::println);

        threads = new Thread[5];
        count = 0;

    }



    public static String readMessage(){

        String mensaje;

        do{
            System.out.print("\n\tIntroduce el mensaje:\n>> ");
            mensaje = sc.nextLine().trim();

            if(mensaje.isEmpty()){
                System.out.println("\n\tEl mensaje no puede estar vacío");
            }

        } while(mensaje.isEmpty());

        return mensaje;
    }


    public static String readAddressee(){

        String addressee;

        do{
            System.out.print("\n\tIntroduce el destinatario:\n>> ");
            addressee = sc.nextLine().trim();

            if(addressee.isEmpty()){
                System.out.println("\n\tEl destinatario no puede estar vacío");
            }

        } while(addressee.isEmpty());

        return addressee;
    }



}

