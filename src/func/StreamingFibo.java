package func;

/*
    Demonstrate how to compute the Fibonacci series with a UnaryOperator
*/

import java.util.stream.Stream;

public class StreamingFibo {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 92) {
            Stream.iterate(new long[]{1, 1}, e -> new long[]{e[1], e[0] + e[1]})
                    .limit(i++)
                    .forEach(f -> System.out.print(f[0] + " "));
            System.out.println();
        }
    }
}
