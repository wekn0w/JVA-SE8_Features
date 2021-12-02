package stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class IntStreamExample {

    public static IntStream intStream() {
        return IntStream.of(2, 3, 3, 4);
    }

    public static void log(int i) {
        System.out.println(i);
    }

    /**
     * Find and print:
     * - max number
     * - average number
     * - list of distinct values: "2,3,4"
     */
    public static void main(String[] args) {
        int max = intStream().max().getAsInt();
        System.out.println(max);
        assertEquals(max, 4);

        int avg = (int) intStream().average().getAsDouble();
        System.out.println(avg);
        assertEquals(avg, 3);

        String distinct = intStream().distinct().
                boxed().map(s -> s.toString()).collect(Collectors.joining(","));
        System.out.println(distinct);
        assertEquals(distinct, "2,3,4");

    }
}

