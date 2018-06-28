package pl.laskowski.marcin.model.dividers;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class BlockDivider implements SudokuDivider {

    private final int sizeX;
    private final int sizeY;

    public BlockDivider(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        Set<Region> regions = new HashSet<>();
        for (int x = 0; x < sudoku.sizeX(); x += sizeX) {
            for (int y = 0; y < sudoku.sizeY(); y += sizeY) {
                regions.add(createRegionAt(sudoku, x, y));
            }
        }
        return regions;
    }

    private Region createRegionAt(Sudoku sudoku, int startX, int startY) {
        List<Field> fields = new ArrayList<>();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Field field = sudoku.at(startX + x, startY + y);
                fields.add(field);
            }
        }
        return new Region(fields);
    }

}
