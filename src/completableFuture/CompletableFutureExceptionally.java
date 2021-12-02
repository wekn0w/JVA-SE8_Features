package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionally {

    /*
        The exceptionally() callback gives you a chance to recover from errors
        generated from the original Future. You can log the exception here
        and return a default value.
        ------------------------------------
        You might need to run this class several times to observe an exception
     */
    public static void main(String[] args) throws Exception {

        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrementException)
                .thenApply(CompletableFutureUtils::slowIncrement)
                // this function will be executed only in case of an exception
                .exceptionally(ex -> {
                    System.out.println("exception occured!");
                    return 0;
                })
                .thenApply(CompletableFutureUtils::slowIncrement);

        Integer result = future.get();
        System.out.println(result);

    }

}
