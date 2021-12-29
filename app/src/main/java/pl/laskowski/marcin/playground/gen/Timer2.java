package pl.laskowski.marcin.playground.gen;

import pl.laskowski.marcin.model.Sudoku;

/**
 * Created by Marcin Laskowski.
 */

public class Timer2 {

    private final SudokuRunnable r;

    public Timer2(SudokuRunnable r) {
        this.r = r;
    }

    public void measure(ResultListener listener) {
        if (r == null) throw new IllegalStateException("set runnable first");
        long startTime = System.nanoTime();
        Sudoku s = r.run();
        long duration = System.nanoTime() - startTime;
        double durationInMs = duration / 1_000_000.0;
        listener.onResultReady(s, durationInMs);
    }

    @FunctionalInterface
    public interface SudokuRunnable {
        Sudoku run();
    }

    @FunctionalInterface
    public interface ResultListener {
        void onResultReady(Sudoku sudoku, double time);
    }

}
