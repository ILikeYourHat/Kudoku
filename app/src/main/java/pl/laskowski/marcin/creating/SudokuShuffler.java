package pl.laskowski.marcin.creating;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.type.ClassicRectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuShuffler {

    private final Random random;
    private final SudokuTransformations transformations = new SudokuTransformations();

    public SudokuShuffler() {
        random = new Random();
    }

    public SudokuShuffler(long seed) {
        random = new Random(seed);
    }

    public Sudoku shuffle(Sudoku sudoku, ClassicRectangle variant) {
        sudoku = rotateAndMirror(sudoku);
        sudoku = swapNumbers(sudoku, variant.regionSize());
        sudoku = swapColumns(sudoku, variant);
        sudoku = swapRows(sudoku, variant);
        return sudoku;
    }

    public Sudoku shuffleFull(Sudoku sudoku, ClassicRectangle variant){
        sudoku = swapColumns(sudoku, variant);
        sudoku = swapRows(sudoku, variant);
        return sudoku;
    }

    private Sudoku rotateAndMirror(Sudoku sudoku) {
        switch (random.nextInt(8)) {
            case 0:
                return sudoku.copy();
            case 1:
                return transformations.rotateLeft(sudoku);
            case 2:
                return transformations.rotate180(sudoku);
            case 3:
                return transformations.rotateRight(sudoku);
            case 4:
                return transformations.mirrorByXAxis(sudoku);
            case 5:
                return transformations.mirrorByYAxis(sudoku);
            case 6:
                return transformations.mirrorByFirstDiagonal(sudoku);
            case 7:
                return transformations.mirrorBySecondDiagonal(sudoku);
            default:
                throw new IllegalStateException();
        }
    }

    private Sudoku swapNumbers(Sudoku sudoku, int maxValue) {
        List<Integer> possibleValues = shuffledValues(maxValue);
        sudoku.forEach(field -> {
            if (!field.isEmpty()) {
                int currentValue = field.value();
                int newValue = possibleValues.get(currentValue - 1);
                field.set(newValue);
            }
        });
        return sudoku;
    }

    private Sudoku swapColumns(Sudoku sudoku, ClassicRectangle variant) {
        List<List<Field>> shuffled = shuffleGroups(sudoku.columns(), variant.getBlockWidth());
        return sudoku.copyWithIndexMapping(p -> {
            Field f = shuffled.get(p.x()).get(p.y());
            return new Point(f.getX(), f.getY());
        });
    }

    private Sudoku swapRows(Sudoku sudoku, ClassicRectangle variant) {
        List<List<Field>> shuffled = shuffleGroups(sudoku.rows(), variant.getBlockHeight());
        return sudoku.copyWithIndexMapping(p -> {
            Field f = shuffled.get(p.y()).get(p.x());
            return new Point(f.getX(), f.getY());
        });
    }

    private List<Integer> shuffledValues(int to) {
        List<Integer> values = new ArrayList<>();
        for (int value = 1; value <= to; value++) {
            values.add(value);
        }
        Collections.shuffle(values, random);
        return values;
    }

    private <T> List<T> shuffleGroups(List<T> origin, int size) {
        List<T> result = new ArrayList<>();
        List<List<T>> groups = new ArrayList<>();
        for (int i = 0; i < origin.size(); i += size) {
            groups.add(origin.subList(i, i + size));
        }
        Collections.shuffle(groups, random);
        for (List<T> group : groups) {
            Collections.shuffle(group, random);
            result.addAll(group);
        }
        return result;
    }

}
