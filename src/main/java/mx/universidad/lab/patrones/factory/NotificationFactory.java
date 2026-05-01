package factory;
import model.*;

public class NotificationFactory{

    public static Notification create(String tipo, String mensaje){

        switch(tipo)
        {
            case "SMS":
                return new SMSNotification(mensaje);
            case "EMAIL":
                return new EmailNotification(mensaje);
            case "PUSH":
                return new PushNotification(mensaje);
            default:
                throw new IllegalArgumentException("Tipo no valido: " + tipo);
        }

    }
}