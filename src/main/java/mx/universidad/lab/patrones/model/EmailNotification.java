package model;

public class EmailNotification implements Notification{

    private String mensaje;

    public EmailNotification(String mensaje){
        this.mensaje = mensaje;
    }

    @Override 
    public void send(String destinatario){
        System.out.println("[EMAIL] Para: " + destinatario + " | Msg: " +  mensaje);
    }

    @Override
    public String getType(){
        return "EMAIL";
    }
}