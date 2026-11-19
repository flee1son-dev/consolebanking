package flee1son.dev.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ShutdownManager{
    private final AtomicBoolean running;

    public ShutdownManager(AtomicBoolean running) {
        this.running = running;
    }

    public void shutdown(){
        running.set(false);
        System.out.println("Shutting down...");
    }

    public boolean isRunning(){
        return running.get();
    }
}
