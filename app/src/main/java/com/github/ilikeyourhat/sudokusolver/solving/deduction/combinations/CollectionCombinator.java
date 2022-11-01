package com.github.ilikeyourhat.sudokusolver.solving.deduction.combinations;

import java.util.*;

public class CollectionCombinator {

    private final int combinationSize;

    public CollectionCombinator(int combinationSize) {
        this.combinationSize = combinationSize;
    }

    @SuppressWarnings("unchecked")
    public <T> void iterate(Collection<T> collection, CombinationConsumer<T> consumer) {
        if (collection.size() < combinationSize) return;
        CombinationIterator iterator = new CombinationIterator(combinationSize, collection.size());
        T[] elements = (T[]) collection.toArray();
        while (iterator.hasNext()) {
            List<T> values = new ArrayList<>();
            for (int index : iterator.next()) {
                T value = elements[index];
                values.add(value);
            }
            consumer.onNextCombination(values);
        }
    }

    @FunctionalInterface
    public interface CombinationConsumer<T> {
        void onNextCombination(List<T> values);
    }

}
