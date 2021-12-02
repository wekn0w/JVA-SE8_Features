package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureHandle {

    /*
        The API also provides a more generic method handle() to recover
        from exceptions. It is called whether or not an exception occurs.
        ----------------------------------------------------------------------
        You might need to run this class several times to observe an exception 
     */
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(CompletableFutureUtils::slowInit)
                .thenApply(CompletableFutureUtils::slowIncrementException)
                .handle((ok, ex) -> {
                    if (ex != null) {
                        System.out.println("exception occured");
                    }
                    // 0 is the flag that may enable further processing
                    // by other dependent stages downstream
                    return ok == null ? 0 : ok;    // return 0 if exception
                })
                .thenApply(CompletableFutureUtils::slowIncrement);

        Integer result = future.get();  // 3 if no exception, and 1 otherwise
        System.out.println(result);

    }

}
