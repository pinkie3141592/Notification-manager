package singleton;
import java.util.ArrayList;
import java.util.List;

public class NotificationLogger{

    private static NotificationLogger instance;

    private List<String> logs;

    private NotificationLogger(){

        logs = new ArrayList<>();

    }


    public static synchronized NotificationLogger getInstance(){

        if(instance == null)
            instance = new NotificationLogger();
        
        return instance;
    }

    public synchronized void log(String mensaje){

        logs.add(mensaje);
        System.out.println("[LOG]" + mensaje);
        
    }

    public List<String> getLogs(){
        return logs;
    }




}