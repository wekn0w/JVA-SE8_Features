package completableFuture;

import java.util.Random;

/*
 * just methods for demonstration
 */

public class CompletableFutureUtils {

    public static Integer slowInit() {
        System.out.println("started task slowInit()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return 1;
    }

    public static Integer slowIncrement(Integer i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("finished incrementing with the result " + (i + 1));
        return 1 + i;
    }

    public static Integer slowIncrementException(Integer i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        Random rand = new Random();
        if (rand.nextInt(10) > 3)
            throw new CustomException(i);
        return i + 1;
    }

}
