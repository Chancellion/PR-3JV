package Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FactorialTask implements Callable<Long> {
    private int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public Long call() throws Exception {
        return factorial(number);
    }

    private Long factorial(int n) {
        if (n == 0) {
            return 1L;
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

public class FactorialApp {
    public static void main(String[] args) {
        int[] numbers = {5, 10, 15, 20, 25};
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> results = new ArrayList<>();

        for (int number : numbers) {
            FactorialTask task = new FactorialTask(number);
            Future<Long> result = executor.submit(task);
            results.add(result);
        }

        executor.shutdown();

        for (Future<Long> future : results) {
            try {
                System.out.println("Факторiал: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
