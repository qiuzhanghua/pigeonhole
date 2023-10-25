package dev.taiji;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    static int[] smalls = {1, 2, 3, 4, 5, 6, 7, 8};
    static int[] larges = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
    static int[] holes = {9, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11};
    static long count = 0;
    static int cores = Runtime.getRuntime().availableProcessors();

    static List<int[]> v1 = new ArrayList<>();

    public static void main(String[] args) {
        Instant start = Instant.now();
        permuteSmalls(8);
        count = permuteLarge(7);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Time used: %d ms\n", timeElapsed);
        System.out.printf("Result = %d\n", count * 2);
    }

    public static long permuteLarge(int maxDepth) {
        Long count = 0L;
        try (ExecutorService executorService = Executors.newFixedThreadPool(cores)) {
            List<Future<Long>> futures = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                int index = i;
                Future<Long> submit = executorService.submit(() -> {
//                    int[] holes = {9, 10, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 11};
                    int[] holes = v1.get(index);
                    boolean[] used = new boolean[larges.length];
                    int[] result = new int[maxDepth];
                    int depth = 0;
                    return backTrackingLarges(holes, larges.clone(), result, depth, used);
                });
                futures.add(submit);
            }
            for (Future<Long> future : futures) {
                count += future.get();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public static long backTrackingLarges(int[] holes, int[] larges, int[] result, int depth, boolean[] used) {
        if (depth == result.length) {
//            System.out.println(Arrays.toString(holes));
            return 1L;
        }
        long count = 0L;
        for (int i = 0; i < larges.length; i++) {
            if (!used[i]) {
                result[depth] = larges[i];
                int index = 3 + depth * 2;
                holes[index] = larges[i];
                if (holes[index - 1] * larges[i] > 99 || holes[index + 1] * larges[i] > 99) {
                    break;
                }
                used[i] = true;
                count += backTrackingLarges(holes, larges, result, depth + 1, used);
                used[i] = false;
            }
        }
        return count;
    }



    public static void permuteSmalls(int num) {
        boolean[] used = new boolean[smalls.length];
        int[] result = new int[num];
        int depth = 0;
        backTrackingSmalls(result, depth, used);
    }

    public static void backTrackingSmalls(int[] v, int depth, boolean[] used) {
        if (depth == v.length) {
            v1.add(holes.clone());
//            System.out.println(Arrays.toString(holes));
            return;
        }
        for (int i = 0; i < smalls.length; i++) {
            if (!used[i]) {
                v[depth] = smalls[i];
                int index = 2 + depth * 2;
                holes[index] = smalls[i];
                used[i] = true;
                backTrackingSmalls(v, depth + 1, used);
                used[i] = false;
            }
        }
    }

}