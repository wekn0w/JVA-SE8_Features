package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureSeveralPipelines {

    public static void main(String[] args) throws Exception {

        long start = System.nanoTime();

        // Optional: add Thread.currentThread().getName() to slowInit(),
        //           then comment out everything else here; then uncomment res1.
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement);

        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit);

        Integer res0 = CompletableFutureUtils.slowInit();    // here we do the work by ourselves...

        Integer res1 = future1.get(); // ... here we extract results...
        Integer res2 = future2.get();

        long elapsedTime = System.nanoTime() - start;

        System.out.println("tasks finished with results "
                + res0 + ", " + res1 + " and " + res2);  // ... and combine them

        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(TimeUnit.NANOSECONDS.toSeconds(elapsedTime), 2);

    }

}
