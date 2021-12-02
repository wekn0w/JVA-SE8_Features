package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureApplyToWinner {

    /*
     * Take the fastest of future1 and future2 and increment the result
     */
    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();

        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement)
                .thenApply(CompletableFutureUtils::slowIncrement);

        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement);

        CompletableFuture<Integer> winner
                = future1.applyToEither(future2, CompletableFutureUtils::slowIncrement);

        int result = winner.get();
        System.out.println("result: " + result);            // result: 3

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(result, 3);
        assertEquals(TimeUnit.NANOSECONDS.toSeconds(elapsedTime), 3);

    }

}
