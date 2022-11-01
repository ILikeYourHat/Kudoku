package pl.laskowski.marcin.solving.deduction.solver;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.hint.SudokuHintGrid;
import pl.laskowski.marcin.solving.SudokuSolver;

import java.util.*;

public class ParallelDeductionSolver2 implements SudokuSolver {

    private final SudokuSolver solver;

    public ParallelDeductionSolver2(DeductionSolver solver) {
        this.solver = solver;
    }

    @Override
    public Sudoku solve(Sudoku sudoku) {
        return solveInParallel(sudoku);
    }

    private Sudoku solveInParallel(Sudoku sudoku) {
        Queue<Sudoku> queue = new ArrayDeque<>();
        queue.add(sudoku);

        while (!queue.isEmpty()) {
            Sudoku chosen = queue.poll();
            Sudoku result = solver.solve(chosen);
            if (result.isSolved()) {
                return result;
            } else {
                queue.addAll(permutations(result));
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

    private Field getFieldWithLowestPossibilities(Sudoku sudoku, SudokuHintGrid possibilities) {
        return sudoku.getAllFields()
                .stream()
                .filter(Field::isEmpty)
                .min(Comparator.comparingInt(f -> possibilities.forField(f).size()))
                .orElse(null);
    }
}
