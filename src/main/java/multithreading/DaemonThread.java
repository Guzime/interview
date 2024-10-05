package multithreading;

public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Working thread is " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });


        System.out.println("Starting daemon thread " + thread.isDaemon());
        thread.setDaemon(true); // Когда ставим поток демон, если настанет момент,
        // что все потоки бдут потоками демонами, то приложение завершиться
        // Можно сказать, что он фоновый процесс, который нужно завершит сразу -> например отрисовка интерфейса
        // Добавим, что этот признак надо ставить до старта потока
        thread.start();
        System.out.println("Main thread is finished, which demon? " + Thread.currentThread().isDaemon());

    }
}
