package pl.laskowski.marcin.playground;

import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.solving.ConcurrentSudokuSolver;
import pl.laskowski.marcin.solving.SudokuSolver;

import java.util.LinkedHashMap;
import java.util.Map;

public class TimeComparator {

    private TestSet set;
    private Map<String, SolverFactory> tasks;

    public TimeComparator(TestSet set) {
        this.set = set;
        this.tasks = new LinkedHashMap<>();
    }

    public TimeComparator add(String tag, SolverFactory solverFactory) {
        tasks.put(tag, solverFactory);
        return this;
    }

    public void run() {
        dryRun();
        StringBuilder solution = new StringBuilder();
        tasks.keySet().forEach(tag -> solution.append(tag).append(' '));
        solution.append('\n');
        for (Sudoku sudoku : set) {
            tasks.forEach((tag, solverFactory) -> {
                double time = run(solverFactory, sudoku);
                solution.append(String.format("%.3f ", time));
            });
            solution.append('\n');
        }
        System.out.println(solution.toString());
    }

    private void dryRun() {
        for (Sudoku sudoku : set) {
            tasks.forEach((tag, solverFactory) -> {
                run(solverFactory, sudoku);
            });
        }
    }

    private double run(SolverFactory solverFactory, Sudoku sudoku) {
        SudokuSolver solver = solverFactory.create();
        double time = new Timer("X", () -> solver.solve(sudoku)).measure();
        if (solver instanceof ConcurrentSudokuSolver) {
            ((ConcurrentSudokuSolver) solver).shutdown();
        }
        System.gc();
        return time;
    }

    public interface SolverFactory {
        SudokuSolver create();
    }

}
