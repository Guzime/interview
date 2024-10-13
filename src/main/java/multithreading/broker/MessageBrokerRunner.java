package multithreading.broker;

public class MessageBrokerRunner {
    public static void main(String[] args) {
        final int brokerMasStoredMessages = 5;
        final MessageBroker messageBroker = new MessageBroker(brokerMasStoredMessages);
        final Thread producingThread = new Thread(new MessageProducingTask(messageBroker));
        final Thread consumingThread = new Thread(new MessageConsumingTask(messageBroker));
        producingThread.start();
        consumingThread.start();
    }
}
