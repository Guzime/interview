package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static multithreading.SyncWithReentrantLock.Counter.decrement;
import static multithreading.SyncWithReentrantLock.Counter.increment;

public class SyncWithReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Counter counter = new Counter(lock);
        Thread incrementThead = new Thread(applyIntConsumerForCounter(counter, i -> increment()));
        Thread decrementThead = new Thread(applyIntConsumerForCounter(counter, i -> decrement()));

        incrementThead.start();
        decrementThead.start();

        incrementThead.join();
        decrementThead.join();

        System.out.println("Threads finish working and count = " + counter.getCount());
    }


    public static Runnable applyIntConsumerForCounter(Counter counter, IntConsumer consumer) {
        return () -> {
            try {
                // Здесь можем вызывать tryLock(), который будет пытаться вызвать блокировку,
                // чтобы текущий поток не удерживался в состоянии BLOCKED
                counter.lock();
                IntStream.range(0, 10).forEach(consumer);
            } finally {
                counter.unlock();
            }
        };
    }

    public static class Counter {
        private static Integer count;
        private final Lock lock;

        public void lock() {
            lock.lock();
        }

        public void unlock() {
            lock.unlock();
        }

        public boolean tryLock() {
            return lock.tryLock();
        }

        public Integer getCount() {
            return count;
        }

        public Counter(ReentrantLock reentrantLock) {
            count = 0;
            lock = reentrantLock;
        }

        public static void increment() {
            count++;
            System.out.println(currentThread().getName() + " increment count to " + count);
        }

        public static void decrement() {
            count--;
            System.out.println(currentThread().getName() + " decrement count to " + count + " with iteration ");
        }
    }
}
