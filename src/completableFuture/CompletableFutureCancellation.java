package completableFuture;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureCancellation extends CompletableFutureUtils {

    /*
        A CompletableFuture task can be cancelled.
     */
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrement);

        future.cancel(true);   // can predicate on a condition, too

        System.out.println(future.isCancelled());              // true
        System.out.println(future.isCompletedExceptionally()); // true

        try {
            Integer result = future.get(); // results in CancellationException
            System.out.println(result);
        } catch (CancellationException ce) {
            System.out.println("Caught CE!");
        }

    }

}
