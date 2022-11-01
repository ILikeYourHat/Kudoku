package com.github.ilikeyourhat.sudokusolver.solving.deduction.solver;

import com.github.ilikeyourhat.sudokusolver.model.Field;
import com.github.ilikeyourhat.sudokusolver.model.Sudoku;
import com.github.ilikeyourhat.sudokusolver.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver;

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
