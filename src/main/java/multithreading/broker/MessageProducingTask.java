package multithreading.broker;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class MessageProducingTask implements Runnable {
    private final MessageBroker messageBroker;
    private final MessageFactory messageFactory;

    public MessageProducingTask(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
        this.messageFactory = new MessageFactory();
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                Message producingMessage = messageFactory.create();
                messageBroker.produce(producingMessage);
                System.out.println("Producing message:" + producingMessage);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }

    }

    private final static class MessageFactory {
        private int nextMessageIndex;

        public MessageFactory() {
            nextMessageIndex = 1;
        }

        public Message create() {
            return new Message("Message#" + nextMessageIndex++);
        }
    }

}
