package com.github.ilikeyourhat.sudokusolver.solving.bruteforce;

import com.github.ilikeyourhat.sudokusolver.model.Field;
import com.github.ilikeyourhat.sudokusolver.model.Sudoku;
import com.github.ilikeyourhat.sudokusolver.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.sudokusolver.solving.ConcurrentSudokuSolver;
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver;
import org.jetbrains.annotations.NotNull;
import com.github.ilikeyourhat.sudokusolver.model.Field;
import com.github.ilikeyourhat.sudokusolver.model.Sudoku;
import com.github.ilikeyourhat.sudokusolver.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.sudokusolver.solving.ConcurrentSudokuSolver;
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BruteForceConcurrentSolver implements ConcurrentSudokuSolver {

    private final SudokuSolver coreAlgorithm;
    private final ThreadPoolExecutor executors;
    private final int sampleSize;

    public BruteForceConcurrentSolver(int threads, int sampleSize) {
        if (threads < 1) throw new IllegalArgumentException("There must be at least one thread");
        this.coreAlgorithm = new BruteForceSolver();
        this.executors = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
        this.sampleSize = sampleSize;
    }

    @NotNull
    @Override
    public Sudoku solve(@NotNull Sudoku sudoku) {
        try {
            BlockingQueue<Sudoku> queue = new LinkedBlockingQueue<>();
            queue.add(sudoku);
            insertSamples(queue);
            return solveInParallel(queue);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (UnexpectedSuccessException e) {
            return e.getSolution();
        }
    }

    private void insertSamples(BlockingQueue<Sudoku> queue) throws InterruptedException {
        while (queue.size() < sampleSize) {
            Sudoku sample = queue.take();
            List<Sudoku> dividedSample = divide(sample);
            queue.addAll(dividedSample);
        }
    }

    private List<Sudoku> divide(Sudoku sample) {
        SudokuHintGrid hintGrid = SudokuHintGrid.createAndReduce(sample);
        Field field = sample.getFirstEmptyField();
        if (field != null) {
            return hintGrid.forField(field).stream()
                    .map(possibility -> {
                        Sudoku copy = sample.copy();
                        copy.at(field.position()).set(possibility);
                        return copy;
                    })
                    .collect(Collectors.toList());
        } else {
            throw new UnexpectedSuccessException(sample);
        }
    }

    private Sudoku solveInParallel(BlockingQueue<Sudoku> queue) throws InterruptedException, ExecutionException {
        ExecutorCompletionService<Sudoku> service = new ExecutorCompletionService<>(executors);
        List<Future<Sudoku>> futures = IntStream.range(0, executors.getMaximumPoolSize())
                .mapToObj(i -> solveTask(queue))
                .map(service::submit)
                .collect(Collectors.toList());
        try {
            return service.take().get();
        } finally {
            for (Future<Sudoku> f : futures) f.cancel(true);
        }
    }

    private Callable<Sudoku> solveTask(BlockingQueue<Sudoku> queue) {
        return () -> {
            while (!Thread.interrupted()) {
                Sudoku result = coreAlgorithm.solve(queue.take());
                if (result.isSolved()) return result;
            }
            return null;
        };
    }

    @Override
    public void shutdown() {
        try {
            this.executors.shutdownNow();
            //noinspection ResultOfMethodCallIgnored
            this.executors.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            //ignore
        }
    }

}
