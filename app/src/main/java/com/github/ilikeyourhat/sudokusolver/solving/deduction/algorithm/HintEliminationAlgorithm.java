package com.github.ilikeyourhat.sudokusolver.solving.deduction.algorithm;

import com.github.ilikeyourhat.sudokusolver.model.Field;
import com.github.ilikeyourhat.sudokusolver.model.Region;
import com.github.ilikeyourhat.sudokusolver.model.hint.SudokuHintGrid;

import java.util.List;

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
