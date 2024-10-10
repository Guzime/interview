package multithreading;

import static java.util.stream.IntStream.range;

public class RaceCondition {
    public static int commonResource = 0;
    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> range(0, 600).forEach(x -> incrementCounter());

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        //Каждый раз будет разный результат, ибо операция commonResource++ не атомарная,
        //а состоит из 3 атомарных операций (1 - чтение из памяти commonResource,
        //2 - добавление в память + 1, 3 - запись обратно в память).
        //Получается что между 1 и 2 может произойти "грязное считывание"
        System.out.println(commonResource);
    }

    // Код выполняемый в ключевом слове synchronized может выполняться только одним поток в произвольный квант времени
    public static synchronized void incrementCounter() {
        commonResource++;
    }
}
