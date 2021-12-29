package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class MainDiagonalDivider implements SudokuDivider {

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        int limit = sudoku.sizeX() < sudoku.sizeY() ? sudoku.sizeX() : sudoku.sizeY();
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            fields.add(sudoku.at(i, i));
        }
        Region region = new Region(fields);
        return Collections.singleton(region);
    }

}
