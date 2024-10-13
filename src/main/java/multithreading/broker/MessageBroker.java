package multithreading.broker;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class MessageBroker {
    private final Queue<Message> messagesToBeConsumed;
    private final int maxStoredMessages;

    public MessageBroker(int maxStoredMessages) {
        this.messagesToBeConsumed = new ArrayDeque<>(maxStoredMessages);
        this.maxStoredMessages = maxStoredMessages;
    }

    public synchronized void produce(Message message) {
        messagesToBeConsumed.add(message);
    }

    public synchronized Message consumed() {
        return messagesToBeConsumed.poll();
    }
}
