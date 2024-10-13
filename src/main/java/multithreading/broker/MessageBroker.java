package multithreading.broker;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Thread.currentThread;

public class MessageBroker {
    private final Queue<Message> messagesToBeConsumed;
    private final int maxStoredMessages;

    public int getSize() {
        return messagesToBeConsumed.size();
    }
    public MessageBroker(int maxStoredMessages) {
        this.messagesToBeConsumed = new ArrayDeque<>(maxStoredMessages);
        this.maxStoredMessages = maxStoredMessages;
    }

    public synchronized void produce(Message message) {
        try {
            while (messagesToBeConsumed.size() >= maxStoredMessages) {
                wait();
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        notify();
        messagesToBeConsumed.add(message);
    }

    public synchronized Message consumed() {
        try {
            while (messagesToBeConsumed.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        notify();

        return messagesToBeConsumed.poll();
    }
}
