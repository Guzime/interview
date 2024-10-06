package multithreading;

import java.lang.Thread.UncaughtExceptionHandler;

import static java.lang.Thread.currentThread;

public class UncaughtException {
    public static void main(String[] args) {

        UncaughtExceptionHandler handler = (thread, exception) -> {
            System.out.println("Catch exception: \n" + exception.getMessage()
                    +"\nfrom thread " + thread.getName());
        };

        Thread thread = new Thread(()-> {
           throw new RuntimeException("Throw uncaught exception from " + currentThread().getName());
        });
        //По дефолту никакие исключения не ловятся, поток просто заканчивает свое существование
        thread.setUncaughtExceptionHandler(handler);
        thread.start();
    }
}
