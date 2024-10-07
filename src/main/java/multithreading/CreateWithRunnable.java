package multithreading;


public class CreateWithRunnable {
    public static void main(String[] args) {
        final Runnable task = () -> System.out.println(Thread.currentThread().getName());
        final Thread thread = new Thread(task);
        thread.start();

        final Runnable taskDisplayThreadName = () ->
                System.out.println(Thread.currentThread());

        //Здесь треды создаеются (отпачковываются) main -> Thread-0 -> Thread - 2 ... 12
        final Runnable taskCreatingThreads = () -> {
            for (int i = 0; i < 10; i++) {
                final Thread subThread = new Thread(taskDisplayThreadName);
                subThread.start();
            }
        };
        final Thread threadMain = new Thread(taskCreatingThreads);
        threadMain.start();

    }
}
