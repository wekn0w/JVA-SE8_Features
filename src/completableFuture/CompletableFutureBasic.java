package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureBasic {

    /*
     * Initialize CompletableFuture with slowInit(),
     * then perform slow increment 2 times
     */
    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();

        CompletableFuture<?> future
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement)
                .thenApply(CompletableFutureUtils::slowIncrement)
                .thenAccept(res -> System.out.println("async result: " + res));

        future.get();

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(TimeUnit.NANOSECONDS.toSeconds(elapsedTime), 3);
    }

}
