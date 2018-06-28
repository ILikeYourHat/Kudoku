package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.Collections;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class SingleRegionDivider implements SudokuDivider {

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        return Collections.singleton(new Region(sudoku.getAllFields()));
    }

}
