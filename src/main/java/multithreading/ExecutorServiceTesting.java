package multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.Thread.currentThread;

public class ExecutorServiceTesting {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Thread is --- " + currentThread().getName());
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Callable<String> callable = () -> "Hello Tolmach, i was called from runnable ExecutorService and thread --- " + currentThread().getName();

        Future<String> submit = executor.submit(callable);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
        System.out.println(submit.get());
    }


}
