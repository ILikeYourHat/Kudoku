package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class ComplexDivider implements SudokuDivider {

    private final SudokuDivider[] dividers;

    public ComplexDivider(SudokuDivider... dividers) {
        this.dividers = dividers;
    }

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        Set<Region> regions = new HashSet<>();
        for (SudokuDivider divider : dividers) {
            regions.addAll(divider.divideIntoRegions(sudoku));
        }
        return regions;
    }

}
