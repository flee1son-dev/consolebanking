package com.flee1son_dev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.flee1son_dev.console.OperationsConsoleListener;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);

        OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);

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
