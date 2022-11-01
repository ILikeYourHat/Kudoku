package pl.laskowski.marcin.solving.deduction.algorithm;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.hint.SudokuHintGrid;

import java.util.*;

/**
 * Created by Marcin Laskowski.
 */

public class HiddenSinglesAlgorithm extends DeductionAlgorithm {

    public static class Factory implements DeductionAlgorithm.Factory {

        public HiddenSinglesAlgorithm instance(List<Region> regions, SudokuHintGrid sudokuHintGrid) {
            return new HiddenSinglesAlgorithm(regions, sudokuHintGrid);
        }

    }

    private HiddenSinglesAlgorithm(List<Region> regions, SudokuHintGrid possibilities) {
        super(regions, possibilities);
    }

    @Override
    protected void solve(Region region) {
        for (int value = 1; value <= region.size(); value++) {
            Set<Field> occurrences = new HashSet<>();
            for (Field field : region) {
                if (possibilities.contains(field, value)) {
                    occurrences.add(field);
                }
            }
            if (occurrences.size() == 1) {
                Field field = (Field) occurrences.toArray()[0];
                field.set(value);
                notifyChange();
            }
        }
    }

}
