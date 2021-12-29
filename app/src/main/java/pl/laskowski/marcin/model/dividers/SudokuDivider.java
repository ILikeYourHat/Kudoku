package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public interface SudokuDivider {

    Set<Region> divideIntoRegions(Sudoku sudoku);

}
