package multithreading.broker;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class MessageConsumingTask implements Runnable {
    private final MessageBroker messageBroker;

    public MessageConsumingTask(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(3);
                Message consumedMessage = messageBroker.consumed();
                System.out.println("Consuming message:" + consumedMessage + " size  queue is " + messageBroker.getSize());
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
