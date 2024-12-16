package Task3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeChecker {
    private static ConcurrentHashMap<Integer, Boolean> results = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int number : numbers) {
            executor.submit(() -> {
                boolean isPrime = isPrime(number);
                results.put(number, isPrime);
                System.out.println("Цифра " + number + " є простим: " + isPrime);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Результат: " + results);
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
