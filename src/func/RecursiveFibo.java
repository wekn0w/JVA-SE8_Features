package func;

/*
    Demonstrate how to compute the Fibonacci series by top-down recursion
*/

public class RecursiveFibo {
    void calcFibo(int n) {
        System.out.print(fib(n) + " ");
    }

    int fib(int n) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        int k = 0;
        RecursiveFibo rfd = new RecursiveFibo();
        while (k <= 50) {//it takes too long
            for (int i = 1; i <= k; i++)
                rfd.calcFibo(i);
            k++;
            System.out.println();
        }
    }
}
