package multithreading;

import static java.util.stream.IntStream.rangeClosed;

public class Runner {
    public static final int FROM_FIRST_THREAD = 1;
    public static final int TO_FIRST_THREAD = 500;

    public static final int FROM_SECOND_THREAD = 501;
    public static final int TO_SECOND_THREAD = 1000;

    public static void main(String[] args) throws InterruptedException {
        final TaskSummingNumbers firstTask = new TaskSummingNumbers(FROM_FIRST_THREAD, TO_SECOND_THREAD);
        final TaskSummingNumbers secondTask = new TaskSummingNumbers(FROM_SECOND_THREAD, TO_SECOND_THREAD);
        final Thread firstThread = new Thread(firstTask);
        final Thread secondThread = new Thread(secondTask);
        firstThread.start();
        secondThread.start();

        // Первым свой выввод делает поток main
        // 2 подзадачи вывводят только после того как они сделали свои вычисления поэтому вывводится main
        System.out.println("Результат обработки - " + (firstTask.getResultNumber() + secondTask.getResultNumber()));

        // join дожидается вызова из основного потока поэтому результат обработки будет правильным
        firstThread.join();
        secondThread.join();
        System.out.println("Результат обработки - " + (firstTask.getResultNumber() + secondTask.getResultNumber()));

    }

    private static void startThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable);
        thread.start();
    }

    public static final class TaskSummingNumbers implements Runnable {
        public static final int INIT_VALUE_RES = 0;
        private final int fromNumber;
        private final int toNumber;
        private int resultNumber;

        public int getResultNumber() {
            return resultNumber;
        }

        public TaskSummingNumbers(int fromNumber, int toNumber) {
            this.fromNumber = fromNumber;
            this.toNumber = toNumber;
            resultNumber = INIT_VALUE_RES;
        }

        @Override
        public void run() {
            rangeClosed(fromNumber, toNumber)
                    .forEach(i -> resultNumber += i);
            System.out.println("Я закончил на результате " + resultNumber + " на потоке " + Thread.currentThread().getName());
        }
    }

}
