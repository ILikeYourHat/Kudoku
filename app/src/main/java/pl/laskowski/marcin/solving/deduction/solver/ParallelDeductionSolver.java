package pl.laskowski.marcin.solving.deduction.solver;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.SudokuHintGrid;
import pl.laskowski.marcin.solving.SudokuSolver;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Marcin Laskowski.
 */

public class ParallelDeductionSolver implements SudokuSolver {

    private final SudokuSolver solver;
    private final ExecutorService executors;

    public ParallelDeductionSolver(DeductionSolver solver, int threads) {
        if (threads < 1) throw new IllegalArgumentException("There must be at least one thread");
        this.solver = solver;
        this.executors = Executors.newFixedThreadPool(threads);
    }

    @Override
    public Sudoku solve(Sudoku sudoku) {
        try {
            return solveInParallel(sudoku);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Sudoku solveInParallel(Sudoku sudoku) throws InterruptedException, ExecutionException {
        CompletionService<Sudoku> ecs = new ExecutorCompletionService<>(executors);
        List<Future> futures = new ArrayList<>();
        futures.add(ecs.submit(new SudokuSolveCallable(sudoku)));

        while (!futures.isEmpty()) {
            Future<Sudoku> result = ecs.take();
            futures.remove(result);
            Sudoku sudoku1 = result.get();
            if (sudoku1.isSolved()) {
                cancelAll(futures);
                return sudoku1;
            } else {
                permutations(sudoku1).forEach(sudoku2 ->
                        futures.add(ecs.submit(new SudokuSolveCallable(sudoku2))));
            }
        }
        return null;
    }

    private List<Sudoku> permutations(Sudoku sudoku) {
        SudokuHintGrid hintGrid = new SudokuHintGrid(sudoku);
        Field emptyField = getFieldWithLowestPossibilities(sudoku, hintGrid);
        if (emptyField == null) return Collections.emptyList();
        Set<Integer> possibilities = hintGrid.forField(emptyField);
        List<Sudoku> sudokus = new ArrayList<>();
        for (Integer possibility : possibilities) {
            Sudoku copy = sudoku.copy();
            copy.at(emptyField.getX(), emptyField.getY()).set(possibility);
            sudokus.add(copy);
        }
        return sudokus;
    }

    private void cancelAll(List<Future> futures) {
        futures.forEach(future -> future.cancel(true));
    }

    private class SudokuSolveCallable implements Callable<Sudoku> {

        private final Sudoku sudoku;

        SudokuSolveCallable(Sudoku sudoku) {
            this.sudoku = sudoku;
        }

        @Override
        public Sudoku call() throws Exception {
            return solver.solve(sudoku);
        }

    }

    private Field getFieldWithLowestPossibilities(Sudoku sudoku, SudokuHintGrid possibilities) {
        return sudoku.getAllFields()
                .stream()
                .filter(Field::isEmpty)
                .min(Comparator.comparingInt(f -> possibilities.forField(f).size()))
                .orElse(null);
    }

}
