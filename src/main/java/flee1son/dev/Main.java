package flee1son.dev;


import flee1son.dev.config.AppConfig;
import flee1son.dev.console.OperationConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        OperationConsoleListener listener = context.getBean(OperationConsoleListener.class);

        Thread consoleThread = new Thread(listener, "console-listener");
        consoleThread.start();
        System.out.println("App is running. Enter the operation.");

        try{
            consoleThread.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        context.close();
        System.out.println("Exit!!!");
    }
}