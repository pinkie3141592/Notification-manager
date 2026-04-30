package model;

public class PushNotification implements Notification{
    
    private String mensaje;

    public PushNotification(String mensaje){
        this.mensaje = mensaje;
    }

    @Override 
    public void send(String destinatario){
        System.out.println("[PUSH] Para: " + destinatario + " | Msg: " +  mensaje);
    }

    @Override
    public String getType(){
        return "PUSH";
    }
}