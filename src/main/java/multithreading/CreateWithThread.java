package multithreading;

public class CreateWithThread {
    public static void main(String[] args) {

        // Здесь выведется неявно созданый поток - main
        System.out.println("Неявно созданый поток - " + Thread.currentThread().getName());


        // Поток можно создать двумя способами
        // 1. Наследник от класса Thread

        final Thread myThread = new MyThread();
        System.out.println("Вызываем метод start у обычного Thread extends MyThread");
        myThread.start();

        // С помощью анонимного класса
        // В Java анонимный класс - это класс без имени,
        // который определяется непосредственно внутри другого класса или метода.
        // Он обычно используется для реализации интерфейсов или классов-родителей с небольшим количеством методов,
        // и не требует создания отдельного класса для реализации этих методов.

        final Thread unanimousThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Поток - " + currentThread().getName());
            }
        };
        System.out.println("Вызываем метод start у анонимного класса");
        unanimousThread.start();

        // Если вызовем метод run а не start, то код вызовется в потоке main
        System.out.println("Вызываем метод run у ананимного класса");
        unanimousThread.run();

        System.out.println("Прекратили работу в осносном потоке main");
    }

    private static final class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Поток - " + currentThread().getName());
        }
    }

}
