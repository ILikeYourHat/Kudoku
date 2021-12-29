package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.*;

/**
 * Created by Marcin Laskowski.
 */

public class MainDiagonalDivider implements SudokuDivider {

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        int limit = Math.min(sudoku.sizeX(), sudoku.sizeY());
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            fields.add(sudoku.at(i, i));
        }
        Region region = new Region(fields);
        return Collections.singleton(region);
    }

}
