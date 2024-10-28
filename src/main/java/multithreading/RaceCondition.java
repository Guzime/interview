package multithreading;

import static java.util.stream.IntStream.range;

public class RaceCondition {
    // вот тут очень тонки момент, есть оператор valotile
    // который гарантирует, что изменения увидеят остальные потоки
    // но он не гарантирует того, что операции будут атомарными !!!
    // Для этого можно использовать AtomicInteger()
    public static int commonResource = 0;
    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> range(0, 600).forEach(x -> incrementCounter());

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        //Получается что здесь при запуске захватает монитор всего объекта RaceCondition!!!
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
        /* Если ставим синхронизацию по участку кода, то код
        синхронихируется по этому классу который мы указали
        synchronized (RaceCondition.class) {
            commonResource++;
        }*/
    }
}
