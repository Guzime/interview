package multithreading;

import static java.util.stream.IntStream.range;

public class PriorityThread {
    public static void main(String[] args) {
        // У главного потока приоритет по дефолту 5, (можно установить от 1 до 10)
        // и у всех кто будет отпачковываться от него столько же
        printThreadInfo();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        printThreadInfo();

        Thread maxPriorityThread = new Thread(PriorityThread::printThreadInfo);
        maxPriorityThread.start();

        Thread thread10Priority = new Thread(new TaskPriority());
        thread10Priority.setPriority(Thread.MAX_PRIORITY);
        thread10Priority.start();

        //т.к. поток с меньшим приоритетом но он выведется (выполниться) раньше
        //попытки манипулирования приоритетами потоков обычно является ошибкой
        System.out.println("Main thread finished");
    }

    public static class TaskPriority implements Runnable {

        @Override
        public void run() {
            range(0, 100).forEach(System.out::println);
        }
    }


    private static void printThreadInfo() {
        System.out.println("Thread with name - " + Thread.currentThread().getName()
                + " have priority - " + Thread.currentThread().getPriority());
    }
}
