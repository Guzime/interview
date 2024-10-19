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
                // Заставляем поток ждать, и высвобождаем монитор объекта
                // потому что синхронизированный метод захватит монитор объекта MessageBroker
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
                // spurious wakeup — редкая, но возможная ситуация,
                // когда поток пробуждается из состояния ожидания без явного уведомления,
                // поэтому используем while а не if
                // Еще в целом поток может здесь пробудиться
                // когда условие может не соблюдаться и счтаться null отсюда
                // например когда прооинциализируем 2 потока на чтение
                wait();
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        // Уведомляем другие потоки (В данном случае ProducingThread), что мы высвободили монитор объекта
        // важный момент, что извлечение сообщения messagesToBeConsumed.poll()
        // должно быть до вызова notify() т.к. высвобожденный поток должен увидеть
        // актуальное состояние объекта !!!

        Message message = messagesToBeConsumed.poll();
        notify();
        return message;
    }
}
