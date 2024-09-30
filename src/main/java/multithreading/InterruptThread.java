package multithreading;

import java.util.concurrent.TimeUnit;

public class InterruptThread {
    private static final String MESSAGE_WAS_SENT = "\nMessage was already sent";
    private static final int DURATION = 1;
    private static final String MESSAGE_WAS_RECEIVED = "Response was received";
    private static final String SERVER_WAS_STOP = "Server was stop";
    public static final String THREAD_WAS_INTERRUPTED = "Thread was interrupted";

    public static void main(String[] args) throws InterruptedException {
        Thread communicationThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    doRequest();
                }
            } catch (InterruptedException e) {
                System.out.println(THREAD_WAS_INTERRUPTED);
                System.out.println(Thread.currentThread().isInterrupted());
                //вывведет false поэтому нужно использовать
                // Thread.currentThread().interrupt()
            }

        });
        communicationThread.start();

        Thread stoppingThread = new Thread(() -> {
            //communicationThread.stop(); // его использовать нельзя нужно испольовать interrupt
            communicationThread.interrupt();
            stopServer();
        });
        TimeUnit.SECONDS.sleep(5);
        stoppingThread.start();

    }

    public static void doRequest() throws InterruptedException {
        System.out.println(MESSAGE_WAS_SENT + " on the thread - " + Thread.currentThread());
        TimeUnit.SECONDS.sleep(DURATION);
        System.out.println(MESSAGE_WAS_RECEIVED + " on the thread - " + Thread.currentThread());
    }

    private static void stopServer() {
        System.out.println(SERVER_WAS_STOP);
    }
}
