package completableFuture;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

public class CompletableFutureCompose {

    /*
     * 1) Define thenCompose as CompletableFuture;
     * 2) Use thenCompose() to put result of future1 into thenCompose();
     * 3) execute slowIncrement() for thenCompose()
     *
     */
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)      // 1
                .thenApply(CompletableFutureUtils::slowIncrement);  // 2

        CompletableFuture<Integer> thenCompose
                = future1.thenCompose(res -> CompletableFuture.supplyAsync(() -> res)
                .thenApply(CompletableFutureUtils::slowIncrement));           // 3

        int result = thenCompose.get();

        System.out.println(result);

        assertEquals(result, 3);
    }

}
