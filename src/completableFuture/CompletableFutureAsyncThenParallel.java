package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureAsyncThenParallel {

    /**
     * Modifying the previous example to make it run for 2 sec
     */
    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();

        CompletableFuture<Integer> initial
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit);
        CompletableFuture<Integer> future1
                = initial.thenApplyAsync(CompletableFutureUtils::slowIncrement);  // changed to thenApplyAsync
        CompletableFuture<Integer> future2
                = initial.thenApplyAsync(CompletableFutureUtils::slowIncrement);
        CompletableFuture<Integer> future3
                = future1.thenCombine(future2, (x, y) -> x * y);

        int result = future3.get();
        System.out.println("result: " + result);

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(TimeUnit.NANOSECONDS.toSeconds(elapsedTime), 2);

    }

}
