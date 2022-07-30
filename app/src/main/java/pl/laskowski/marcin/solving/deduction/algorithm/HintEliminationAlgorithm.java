package pl.laskowski.marcin.solving.deduction.algorithm;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.SudokuHintGrid;

import java.util.List;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class HintEliminationAlgorithm extends DeductionAlgorithm {

    public static class Factory implements DeductionAlgorithm.Factory {

        public HintEliminationAlgorithm instance(List<Region> regions, SudokuHintGrid sudokuHintGrid) {
            return new HintEliminationAlgorithm(regions, sudokuHintGrid);
        }

    }

    private HintEliminationAlgorithm(List<Region> regions, SudokuHintGrid possibilities) {
        super(regions, possibilities);
    }

    @Override
    protected void solve(Region region) {
        for (Field field : region.fullFields()) {
            ensureFullFieldHaveNoHints(field);
            removeHintFromRegion(region, field.value());
        }
    }

    private void ensureFullFieldHaveNoHints(Field field) {
        if (!possibilities.isEmpty(field)) {
            possibilities.clear(field);
            notifyChange();
        }
    }

    private void removeHintFromRegion(Region region, int value) {
        for (Field field : region) {
            if (possibilities.remove(field, value)) {
                notifyChange();
            }
        }
    }

}
