package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class RowDivider implements SudokuDivider {

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        Set<Region> set = new HashSet<>();
        for (int y = 0; y < sudoku.sizeY(); y++){
            Set<Field> fields = new HashSet<>();
            for (int x = 0; x < sudoku.sizeX(); x++) {
                fields.add(sudoku.at(x, y));
            }
            set.add(new Region(fields));
        }
        return set;
    }

}
