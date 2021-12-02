package completableFuture;

import java.util.concurrent.*;

public class FutureJavaOld {

    static int result;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //futureOldStyle();
        futureWithFixedPool();
    }

    public static void futureOldStyle() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                result = CompletableFutureUtils.slowInit();
            }
        };

        t.start();
        t.join();
        System.out.println("futureOldStyle() is done: " + result);
    }

    public static void futureWithFixedPool() throws InterruptedException, ExecutionException {

        Callable<Integer> r = CompletableFutureUtils::slowInit;
        ExecutorService exec = Executors.newFixedThreadPool(10);
        Future<Integer> future = exec.submit(r);

        Integer res = future.get();

        System.out.println("futureWithFixedPool() is done: " + res);
    }
}
