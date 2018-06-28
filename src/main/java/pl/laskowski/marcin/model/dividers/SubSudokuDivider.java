package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class SubSudokuDivider implements SudokuDivider {

    private final Area[] areas;
    private final SudokuDivider divider;

    public SubSudokuDivider(SudokuDivider divider, Area... areas) {
        this.areas = areas;
        this.divider = divider;
    }

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        Set<Region> regions = new HashSet<>();
        for (Area area : areas) {
            Sudoku fragment = sudoku.subSudoku(area.start.x(), area.start.y(), area.end.x(), area.end.y());
            regions.addAll(divider.divideIntoRegions(fragment));
        }
        return regions;
    }

    public static class Area {
        private Point start;
        private Point end;

        public Area(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

    }

}
