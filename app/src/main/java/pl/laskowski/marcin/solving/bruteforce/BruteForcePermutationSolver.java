package pl.laskowski.marcin.solving.bruteforce;

import pl.laskowski.marcin.creating.SudokuTransformations;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.solving.ConcurrentSudokuSolver;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by Marcin Laskowski.
 */

public class BruteForcePermutationSolver implements ConcurrentSudokuSolver {

    private final SudokuSolver coreAlgorithm;
    private final SudokuTransformations shuffler = new SudokuTransformations();
    private final ExecutorService executors;

    public BruteForcePermutationSolver(SudokuVariant sudokuVariant, int threads) {
        if (threads < 1) throw new IllegalArgumentException("There must be at least one thread");
        this.coreAlgorithm = new BruteForceSolver(sudokuVariant);
        this.executors = Executors.newFixedThreadPool(threads);
    }

    @Override
    public SudokuVariant getSudokuVariant() {
        return coreAlgorithm.getSudokuVariant();
    }

    @Override
    public Sudoku solve(Sudoku sudoku) {
        try {
            return solveInParallel(sudoku);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

//    private Sudoku solveInParallel(Sudoku sudoku) {
//        return Observable.just(sudoku)
//                .flatMapIterable(this::createSolvingMethods)
//                .flatMapMaybe(callable -> Maybe.fromCallable(callable)
//                        .subscribeOn(Schedulers.from(executors)))
//                .blockingFirst();
//    }

    private Sudoku solveInParallel(Sudoku sudoku) throws InterruptedException, ExecutionException {
        ExecutorCompletionService<Sudoku> service = new ExecutorCompletionService<>(executors);
        List<Future<Sudoku>> futures = createSolvingMethods(sudoku)
                .stream()
                .map(service::submit)
                .collect(Collectors.toList());

        try {
            return service.take().get();
        } finally {
            for (Future f : futures) f.cancel(true);
        }
    }

    private List<Callable<Sudoku>> createSolvingMethods(Sudoku sudoku) {
        List<Callable<Sudoku>> callables = new ArrayList<>();
        callables.add(() -> solveMirroredByAntidiagonal(sudoku));
        callables.add(() -> solveMirroredByMainDiagonal(sudoku));
        callables.add(() -> solveMirroredByY(sudoku));
        callables.add(() -> solveMirroredByX(sudoku));
        callables.add(() -> solveRotatedRight(sudoku));
        callables.add(() -> solveRotatedLeft(sudoku));
        callables.add(() -> solveInverted(sudoku));
        callables.add(() -> solveNormally(sudoku));
        return callables;
    }

    private Sudoku solveNormally(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        return coreAlgorithm.solve(sudoku);
    }

    private Sudoku solveInverted(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.rotate180(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.rotate180(result);
    }

    private Sudoku solveRotatedLeft(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.rotateLeft(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.rotateRight(result);
    }

    private Sudoku solveRotatedRight(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.rotateRight(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.rotateLeft(result);
    }

    private Sudoku solveMirroredByX(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.mirrorByXAxis(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.mirrorByXAxis(result);
    }

    private Sudoku solveMirroredByY(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.mirrorByYAxis(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.mirrorByYAxis(result);
    }

    private Sudoku solveMirroredByMainDiagonal(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.mirrorByFirstDiagonal(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.mirrorByFirstDiagonal(result);
    }

    private Sudoku solveMirroredByAntidiagonal(Sudoku sudoku) {
        if (Thread.interrupted()) return null;
        Sudoku inverted = shuffler.mirrorBySecondDiagonal(sudoku);
        if (Thread.interrupted()) return null;
        Sudoku result = coreAlgorithm.solve(inverted);
        if (Thread.interrupted()) return null;
        return shuffler.mirrorBySecondDiagonal(result);
    }

    @Override
    public void shutdown() {
        try {
            executors.shutdownNow();
            executors.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            //ignore
        }
    }

}
