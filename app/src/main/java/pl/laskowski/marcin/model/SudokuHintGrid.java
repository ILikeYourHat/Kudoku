package pl.laskowski.marcin.model;

import pl.laskowski.marcin.solving.deduction.algorithm.HintEliminationAlgorithm;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.*;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuHintGrid {

    private Map<Point, Set<Integer>> hintMap;

    public static SudokuHintGrid createAndReduce(Sudoku sudoku, SudokuVariant sudokuVariant) {
        SudokuHintGrid grid = new SudokuHintGrid(sudoku, sudokuVariant);
        HintEliminationAlgorithm algorithm = new HintEliminationAlgorithm.Factory()
                .instance(sudokuVariant.divideIntoRegions(sudoku), grid);
        algorithm.solve();
        return grid;
    }

    public SudokuHintGrid(Sudoku sudoku, SudokuVariant sudokuVariant) {
        this.hintMap = new HashMap<>();
        for (Field f : sudoku) {
            if (f.isEmpty()) {
                hintMap.put(f.position(), allValuesSet(sudokuVariant));
            }
        }
    }

    private Set<Integer> allValuesSet(SudokuVariant sudokuVariant) {
        int regionSize = sudokuVariant.regionSize();
        Set<Integer> possibleValues = new HashSet<>();
        for (int i = 1; i <= regionSize; i++) {
            possibleValues.add(i);
        }
        return possibleValues;
    }

    public Set<Integer> forField(Field field) {
        return new HashSet<>(getFor(field));
    }

    public Set<Integer> forRegion(Region region) {
        Set<Integer> set = new HashSet<>();
        for (Field field : region) {
            set.addAll(getFor(field));
        }
        return set;
    }

    public boolean isEmpty(Field field) {
        return getFor(field).isEmpty();
    }

    public void clear(Field field) {
        getFor(field).clear();
    }

    public boolean remove(Field field, int value) {
        return getFor(field).remove(value);
    }

    public boolean remove(Region region, int value) {
        boolean removed = false;
        for (Field field : region) {
            removed |= getFor(field).remove(value);
        }
        return removed;
    }

    public boolean contains(Field field, int value) {
        return getFor(field).contains(value);
    }

    public void replace(Field field, Set<Integer> hints) {
        Set<Integer> set = getFor(field);
        set.clear();
        set.addAll(hints);
    }

    public void removeAll(Field field, Set<Integer> hintsToRemove) {
        getFor(field).removeAll(hintsToRemove);
    }

    private Set<Integer> getFor(Field field) {
        return hintMap.getOrDefault(field.position(), Collections.emptySet());
    }

}
