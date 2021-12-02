package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompletableFutureAsyncThenSequent {

    /**
     * Runs for 3 sec because async stage is followed by sequential processing
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.nanoTime();

        CompletableFuture<Integer> initial
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit);
        CompletableFuture<Integer> future1
                = initial.thenApply(CompletableFutureUtils::slowIncrement);   // we're wrong!
        CompletableFuture<Integer> future2                                    // should've used
                = initial.thenApply(CompletableFutureUtils::slowIncrement);   // thenApplyAsynch()
        CompletableFuture<Integer> future3
                = future1.thenCombine(future2, (x, y) -> x * y);

        int result = future3.get();
        System.out.println("result: " + result);

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed",
                TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

//******************** This assert will fail ***********************************        
        assertEquals(TimeUnit.NANOSECONDS.toSeconds(elapsedTime), 2);
//******************************************************************************
    }

}
