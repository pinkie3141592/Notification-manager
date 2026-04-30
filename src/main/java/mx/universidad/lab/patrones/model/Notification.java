package model;

public interface Notification{

    void send(String destinatario);

    String getType();

}