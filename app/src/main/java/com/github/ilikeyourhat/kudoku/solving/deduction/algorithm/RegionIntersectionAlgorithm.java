package com.github.ilikeyourhat.kudoku.solving.deduction.algorithm;

import com.github.ilikeyourhat.kudoku.model.Region;
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.kudoku.model.Region;
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.kudoku.solving.deduction.combinations.CollectionCombinator;

import java.util.List;
import java.util.Set;

public class RegionIntersectionAlgorithm extends DeductionAlgorithm {

    public static class Factory implements DeductionAlgorithm.Factory {

        public RegionIntersectionAlgorithm instance(List<Region> regions, SudokuHintGrid sudokuHintGrid) {
            return new RegionIntersectionAlgorithm(regions, sudokuHintGrid);
        }

    }

    private RegionIntersectionAlgorithm(List<Region> regions, SudokuHintGrid possibilities) {
        super(regions, possibilities);
    }

    @Override
    public boolean solve() {
        CollectionCombinator combinator = new CollectionCombinator(2);
        combinator.iterate(regions, values -> {
            Region first = values.get(0);
            Region second = values.get(1);
            solve(first, second);
        });
        return haveChanged();
    }

    @Override
    protected void solve(Region region) {
        throw new UnsupportedOperationException("This algorithm operates on two regions");
    }

    private void solve(Region region1, Region region2) {
        Region intersection = region1.intersect(region2);
        if (!intersection.isEmpty()) {
            region1 = region1.subtract(intersection);
            region2 = region2.subtract(intersection);
            solve(region1, region2, intersection);
        }
    }

    private void solve(Region region1, Region region2, Region intersection) {
        Set<Integer> hintsFromIntersection = possibilities.forRegion(intersection);
        Set<Integer> hintsFromRegion1 = possibilities.forRegion(region1);
        Set<Integer> hintsFromRegion2 = possibilities.forRegion(region2);
        for (Integer value : hintsFromIntersection) {
            if (hintsFromRegion1.contains(value) && !hintsFromRegion2.contains(value)) {
                if (possibilities.remove(region1, value)) notifyChange();
            } else if (!hintsFromRegion1.contains(value) && hintsFromRegion2.contains(value)) {
                if (possibilities.remove(region2, value)) notifyChange();
            }
        }
    }

}
