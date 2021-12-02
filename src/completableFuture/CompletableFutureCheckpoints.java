package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCheckpoints {

    /*
        We can preserve checkpoints.
        ------------------------------------
        You might need to run this class several times to observe exceptions 
     */
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)  // 1
                .thenApply(CompletableFutureUtils::slowIncrement)          // 2
                .thenApply(CompletableFutureUtils::slowIncrementException) // 3
                .exceptionally(ex -> {
                    System.out.println("Exception 1 occured!");
                    return ex.getCause() instanceof CustomException
                            ? ((CustomException) ex.getCause()).getCheckpoint()
                            : 0;
                })
                .thenApply(CompletableFutureUtils::slowIncrement)          // 4
                .thenApply(CompletableFutureUtils::slowIncrementException) // 5
                .exceptionally(ex -> {
                    System.out.println("Exception 2 occured!");
                    return ex.getCause() instanceof CustomException
                            ? ((CustomException) ex.getCause()).getCheckpoint()
                            : 0;
                })
                .thenApply(CompletableFutureUtils::slowIncrement);         // 6

        Integer result = future.get();  // no exceptions: 6
        // a single exception: 5
        // both exceptions: 4
        System.out.println(result);

    }

}
