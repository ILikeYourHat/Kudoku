package pl.laskowski.marcin.solving.deduction.algorithm;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.SudokuHintGrid;

import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class NakedSinglesAlgorithm extends DeductionAlgorithm {

    public static class Factory implements DeductionAlgorithm.Factory {

        public NakedSinglesAlgorithm instance(Set<Region> regions, SudokuHintGrid sudokuHintGrid) {
            return new NakedSinglesAlgorithm(regions, sudokuHintGrid);
        }

    }

    private NakedSinglesAlgorithm(Set<Region> regions, SudokuHintGrid possibilities) {
        super(regions, possibilities);
    }

    @Override
    protected void solve(Region region) {
        for (Field field : region.emptyFields()) {
            Set<Integer> values = possibilities.forField(field);
            if (values.size() == 1) {
                int singleValue = (int) values.toArray()[0];
                field.set(singleValue);
                notifyChange();
            }
        }
    }

}
