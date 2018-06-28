package pl.laskowski.marcin.creating;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.solving.sat.SatSolver;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuGenerator {

    private final SudokuVariant variant;
    private final Random random = new Random();
    private final SatSolver solver;
    private final SudokuRater.Difficulty difficulty;
    private final SudokuRater rater;
    private final Float percentFilled;

    public SudokuGenerator(SudokuVariant variant, SudokuRater.Difficulty difficulty, Float percentFilled) {
        this.variant = variant;
        this.solver = new SatSolver(variant);
        this.difficulty = difficulty;
        this.percentFilled = percentFilled;
        this.rater = new SudokuRater(variant);
    }

    public Sudoku generate() {
        Sudoku empty = variant.template();
        fillSudoku(empty);
        generateHoles(empty);
        return empty;
    }


    private void fillSudoku(Sudoku sudoku) {
        iterateOverFieldsInRandomOrder(sudoku, field -> {
            List<Integer> possibilities = possibilities(variant);
            do {
                int index = random.nextInt(possibilities.size());
                int value = possibilities.remove(index);
                field.set(value);
            } while (solver.checkSolutions(sudoku).equals(SudokuSolutionCount.NONE));
        });
    }

    private void iterateOverFieldsInRandomOrder(Sudoku sudoku, Consumer<Field> consumer) {
        List<Field> fields = sudoku.getAllFields();
        while (!fields.isEmpty()) {
            int index = random.nextInt(fields.size());
            consumer.accept(fields.remove(index));
        }
    }

    private List<Integer> possibilities(SudokuVariant variant) {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= variant.regionSize(); i++) {
            values.add(i);
        }
        return values;
    }

    private void generateHoles(Sudoku sudoku) {
        List<Field> fields = sudoku.getAllFields();
        while (!fields.isEmpty() && !isPercentReached(sudoku)) {
            int index = random.nextInt(fields.size());
            Field field = fields.remove(index);
            int value = field.value();
            field.clear();
            if (!hasOneSolution(sudoku) || isAboveMaxDifficulty(sudoku)) {
                field.set(value);
            }
        }
    }

    private boolean hasOneSolution(Sudoku sudoku) {
        return solver.checkSolutions(sudoku).equals(SudokuSolutionCount.ONE);
    }

    private boolean isPercentReached(Sudoku sudoku) {
        return percentFilled != null && percentFilled > rater.percentFilled(sudoku);
    }

    private boolean isAboveMaxDifficulty(Sudoku sudoku) {
        return difficulty != null && rater.rate(sudoku).harderThan(difficulty);
    }

}
