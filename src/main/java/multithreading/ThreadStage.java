package multithreading;

public class ThreadStage {
    public static void main(String[] args) throws InterruptedException {
        final Thread mainThread = Thread.currentThread();
        showThreadState(mainThread);

        final Thread thread = new Thread(() -> {
            try {
                // join заставляет ждать поток класса thread по сути переводить его в стейдж Waiting
                mainThread.join();

                // Код дальше не выполняется пока на 20 строчке не пройдет sleep и 21 строка еще не выполниться
                showThreadState(Thread.currentThread());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(1000);
        showThreadState(thread);

        System.out.println("Пробуем Terminated stage поймать");
        final Thread threadTerminated = new Thread(() -> {
            // важный момент в том, что другие потоки выполняют дальнейшую работу
            throw new RuntimeException();
        });
        threadTerminated.start();
        threadTerminated.join();
        showThreadState(threadTerminated);
    }

    private static void showThreadState(final Thread thread) {
        System.out.println(thread.getName() + " - " + thread.getState());
    }
}
