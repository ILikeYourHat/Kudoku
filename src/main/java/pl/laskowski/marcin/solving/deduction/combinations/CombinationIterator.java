package pl.laskowski.marcin.solving.deduction.combinations;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Marcin Laskowski.
 */

class CombinationIterator implements Iterator<int[]> {

    private final int collectionSize;
    private int[] current;
    private int[] next;

    CombinationIterator(int sampleSize, int collectionSize) {
        this.current = new int[sampleSize];
        this.next = new int[sampleSize];
        this.collectionSize = collectionSize;
        for (int i = 0; i < next.length; i++) {
            next[i] = i;
        }
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public int[] next() {
        if (next == null) throw new NoSuchElementException();
        System.arraycopy(next, 0, current, 0, next.length);
        incrementIndexes();
        return current;
    }

    private void incrementIndexes() {
        next[next.length-1]++;
        int wrongIndex = findFirstTooBigIndex();
        while (wrongIndex > 0) {
            fixIndex(wrongIndex);
            wrongIndex = findFirstTooBigIndex();
        }
        if (wrongIndex == 0) next = null;
    }

    private void fixIndex(int index) {
        next[index - 1]++;
        for (int i = index; i < next.length; i++) {
            int previousValue = next[i - 1];
            next[i] = previousValue + 1;
        }
    }

    private int findFirstTooBigIndex() {
        for (int i = 0; i < next.length; i++) {
            if (next[i] > biggestIndexFor(next, i)) return i;
        }
        return -1;
    }

    private int biggestIndexFor(int[] array, int position) {
        return collectionSize - array.length + position;
    }

}
