package com.github.ilikeyourhat.kudoku.solving.deduction.algorithm;

import com.github.ilikeyourhat.kudoku.model.Field;
import com.github.ilikeyourhat.kudoku.model.Region;
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.kudoku.model.Field;
import com.github.ilikeyourhat.kudoku.model.Region;
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid;

import java.util.List;
import java.util.Set;

public class NakedSinglesAlgorithm extends DeductionAlgorithm {

    public static class Factory implements DeductionAlgorithm.Factory {

        public NakedSinglesAlgorithm instance(List<Region> regions, SudokuHintGrid sudokuHintGrid) {
            return new NakedSinglesAlgorithm(regions, sudokuHintGrid);
        }

    }

    private NakedSinglesAlgorithm(List<Region> regions, SudokuHintGrid possibilities) {
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
