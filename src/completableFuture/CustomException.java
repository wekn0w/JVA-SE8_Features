package completableFuture;

/*
    This exception keeps some previously calculated value, a "checkpoint".
*/

public class CustomException extends RuntimeException {

    private Integer checkpoint = null;

    public CustomException(Integer checkpoint) {
        this.checkpoint = checkpoint;
    }

    public Integer getCheckpoint() {
        return checkpoint;
    }
}
