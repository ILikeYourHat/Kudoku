package pl.laskowski.marcin.type;

import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.dividers.SubSudokuDivider;
import pl.laskowski.marcin.model.dividers.SudokuDivider;

import static pl.laskowski.marcin.model.dividers.SubSudokuDivider.Area;

/**
 * Created by Marcin Laskowski.
 */

public class Slash extends SudokuVariant {

    private final int widthOffset;
    private final int heightOffset;
    private final SudokuVariant child;

    public Slash(int width, int height, SudokuVariant child) {
        super(width, height);
        this.child = child;
        this.widthOffset = width - child.width();
        this.heightOffset = height - child.height();
        if (widthOffset <= 0 || widthOffset >= child.width()) {
            throw new IllegalArgumentException();
        }
        if (heightOffset <= 0 || heightOffset >= child.height()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int regionSize() {
        return child.regionSize();
    }

    @Override
    protected SudokuDivider divider() {
        return new SubSudokuDivider(child.divider(),
                areaStartingIn(upperLeftCorner()),
                areaStartingIn(lowerRightCorner()));
    }

    @Override
    public Sudoku template() {
        Sudoku sudoku = new Sudoku(width(), height());
        sudoku.append(child.template(), upperLeftCorner());
        sudoku.append(child.template(), lowerRightCorner());
        return sudoku;
    }

    private Area areaStartingIn(Point p) {
        return new Area(p, new Point(p.x() + child.width(), p.y() + child.height()));
    }

    private Point upperLeftCorner() {
        return new Point(0, 0);
    }

    private Point lowerRightCorner() {
        return new Point(widthOffset, heightOffset);
    }

}
