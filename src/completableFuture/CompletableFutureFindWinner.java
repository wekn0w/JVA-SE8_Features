package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureFindWinner {

    /*
     * Find the winner of the competition (it should run for 2 sec and return 2)
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

        CompletableFuture<?> winner
                = CompletableFuture.anyOf(future1, future2);

        System.out.println("result: " + winner.get());            // result: 2

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(winner.get(), 2);
        assertEquals(TimeUnit.NANOSECONDS.toSeconds(elapsedTime), 2);

    }

}
