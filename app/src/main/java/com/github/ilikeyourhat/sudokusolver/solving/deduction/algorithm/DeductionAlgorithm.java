package com.github.ilikeyourhat.sudokusolver.solving.deduction.algorithm;

import com.github.ilikeyourhat.sudokusolver.model.Region;
import com.github.ilikeyourhat.sudokusolver.model.hint.SudokuHintGrid;

import java.util.List;

public abstract class DeductionAlgorithm {

    public interface Factory {
        DeductionAlgorithm instance(List<Region> regions, SudokuHintGrid possibilities);
    }

    final List<Region> regions;
    final SudokuHintGrid possibilities;
    private boolean haveChanged = false;

    DeductionAlgorithm(List<Region> regions, SudokuHintGrid possibilities) {
        this.regions = regions;
        this.possibilities = possibilities;
    }

    public boolean solve() {
        for (Region region : regions) {
            solve(region);
        }
        return haveChanged;
    }

    abstract void solve(Region region);

    void notifyChange() {
        haveChanged = true;
    }

    boolean haveChanged() {
        return haveChanged;
    }

}

