package thread;
import singleton.NotificationLogger;
import model.Notification;
import factory.NotificationFactory;
import auxs.Utilidades;


public class NotificationWorker implements Runnable{

    private String tipo;
    private String destinatario;
    private String mensaje;

    public NotificationWorker(String tipo, String destinatario, String mensaje){

        this.tipo = tipo;
        this.mensaje = mensaje;
        this.destinatario = destinatario;

    }

    @Override
    public void run()
    {

        Notification n  = NotificationFactory.create(tipo, mensaje);

        n.send(destinatario);

        NotificationLogger.getInstance().log(n.getType() + " enviado a " + destinatario + "\n");
    }



}