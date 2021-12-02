package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureCombine {

    /*
     * Compute future3 as sum of future1 and future2 results using thenCombine();
     * make sure task runs for 2 sec
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.nanoTime();

        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement); // 2

        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement); // 2

        CompletableFuture<Integer> future3
                = future1.thenCombine(future2, (x, y) -> x + y);    // 4

        int result = future3.get();

        assertEquals(result, 4);
        System.out.println("result: " + future3.get()); // result: 4

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

    }

}
