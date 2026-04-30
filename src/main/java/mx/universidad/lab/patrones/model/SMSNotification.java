package model;

public class SMSNotification implements Notification{
    private String mensaje;

    public SMSNotification(String mensaje){
        this.mensaje = mensaje;
    }

    @Override 
    public void send(String destinatario){
        System.out.println("[SMS] Para: " + destinatario + " | Msg: " +  mensaje);
    }

    @Override
    public String getType(){
        return "SMS";
    }
}