package pl.laskowski.marcin.playground;

import pl.laskowski.marcin.model.Sudoku;

/**
 * Created by Marcin Laskowski.
 */

public class Timer {

    private final String tag;
    private final SudokuRunnable r;

    public Timer(String tag, SudokuRunnable r) {
        this.tag = tag;
        this.r = r;
    }

    public double measure() {
        if (r == null) throw new IllegalStateException("set1 runnable first");
        return runAndMeasure();
    }

    public void measureOnce() {
        if (r == null) throw new IllegalStateException("set1 runnable first");

        logStart();
        double duration = runAndMeasure();
        log("Duration: %.2f ms", duration);
        logEnd();
    }

    public void measure(int times) {
        if (r == null) throw new IllegalStateException("set1 runnable first");
        if (times < 1) throw new IllegalArgumentException("you must do at least one run");
        logStart();
        log("Times: %d", times);
        double fastest = Double.POSITIVE_INFINITY;
        double slowest = Double.NEGATIVE_INFINITY;
        double sum = 0;

        for (int i = 0; i < times; i++) {
            double duration = runAndMeasure();
            if (duration < fastest) fastest = duration;
            if (duration > slowest) slowest = duration;
            sum += duration;
        }
        double average = sum / times;

        log("Fastest: %.2f ms", fastest);
        log("Slowest: %.2f ms", slowest);
        log("Average: %.2f ms", average);
        log("Total: %.2f ms", sum);
        logEnd();
    }

    private double runAndMeasure() {
        long startTime = System.nanoTime();
        Sudoku s = r.run();
        long duration = System.nanoTime() - startTime;
        if (s.isSolved()) {
            return duration / 1_000_000.0;
        } else {
            return -1;
        }
    }

    private void logStart() {
        System.out.println(String.format("%s started", tag.toUpperCase()));
    }

    private void logEnd() {
        System.out.println(String.format("%s ended", tag.toUpperCase()));
    }

    private void log(String message, Object... args) {
        System.out.println(String.format("> " + message, args));
    }

    public String getTag() {
        return tag;
    }

    @FunctionalInterface
    public interface SudokuRunnable {
        Sudoku run();
    }

}
