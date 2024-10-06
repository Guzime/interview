package multithreading;

public class ThreadStage {
    public static void main(String[] args) throws InterruptedException {
        final Thread mainThread = Thread.currentThread();
        showThreadState(mainThread);

        final Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " state is "
                        + Thread.currentThread().getState() + " which starting just now");
                // join заставляет ждать поток класса thread по сути переводить его в стейдж Waiting
                // Если вызываем mainThread.join(2000) то будет TIMED_WAITING;
                mainThread.join();

                // Код дальше не выполняется пока mainThread не закончит свою работу
                System.out.println(Thread.currentThread().getName() + " state is "
                        + Thread.currentThread().getState() + " which finish work after it waited mainThread");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(thread.getName() + " state is "
                + thread.getState() + " which don't start");
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.getName() + " state is "
                + thread.getState() + " when mainThread sleep");


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
