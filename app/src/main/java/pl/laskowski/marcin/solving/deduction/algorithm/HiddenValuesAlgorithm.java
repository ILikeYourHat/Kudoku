package pl.laskowski.marcin.solving.deduction.algorithm;

import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.solving.deduction.combinations.CollectionCombinator;
import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.SudokuHintGrid;

import java.util.*;

/**
 * Created by Marcin Laskowski.
 */

public class HiddenValuesAlgorithm extends DeductionAlgorithm {

    public static class Factory implements DeductionAlgorithm.Factory {

        private final int size;

        public Factory(int size) {
            this.size = size;
        }

        public HiddenValuesAlgorithm instance(Set<Region> regions, SudokuHintGrid sudokuHintGrid) {
            return new HiddenValuesAlgorithm(regions, sudokuHintGrid, size);
        }

    }

    private final CollectionCombinator combinator;
    private final int size;

    private HiddenValuesAlgorithm(Set<Region> regions, SudokuHintGrid possibilities, int size) {
        super(regions, possibilities);
        this.combinator = new CollectionCombinator(size);
        this.size = size;
    }

    @Override
    protected void solve(Region region) {
        Set<Integer> allHintValues = possibilities.forRegion(region);
        combinator.iterate(allHintValues, result -> {
            Set<Integer> values = new HashSet<>(result);
            int fields = countFieldsThatContainsValues(region, values);
            if (fields == size) {
                removeAllOtherValues(region, values);
            }
        });
    }

    private int countFieldsThatContainsValues(Region region, Set<Integer> values) {
        int sum = 0;
        for (Field field : region) {
            Set<Integer> hints = possibilities.forField(field);
            if (!Collections.disjoint(hints, values)) sum++;
        }
        return sum;
    }

    private void removeAllOtherValues(Region region, Set<Integer> values) {
        for (Field field : region) {
            Set<Integer> hints = possibilities.forField(field);
            int initialSize = hints.size();
            if (!Collections.disjoint(hints, values)) {
                hints.removeIf(element -> !values.contains(element));
            }
            if (initialSize != hints.size()) {
                possibilities.replace(field, hints);
                notifyChange();
            }
        }
    }

}
