package pl.laskowski.marcin.type;

import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.dividers.SubSudokuDivider;
import pl.laskowski.marcin.model.dividers.SudokuDivider;

import static pl.laskowski.marcin.model.dividers.SubSudokuDivider.Area;

/**
 * Created by Marcin Laskowski.
 */

public class Samurai extends SudokuVariant {

    private final SudokuVariant child;

    public Samurai(int width, int height, SudokuVariant child) {
        super(width, height);
        this.child = child;
        if (this.width() % 2 != child.width() % 2) throw new IllegalArgumentException();
        if (this.height() % 2 != child.height() % 2) throw new IllegalArgumentException();
        if (this.width() < 2 * child.width()) throw new IllegalArgumentException();
        if (this.height() < 2 * child.height()) throw new IllegalArgumentException();
        if (this.width() >= 3 * child.width()) throw new IllegalArgumentException();
        if (this.height() >= 3 * child.height()) throw new IllegalArgumentException();
    }

    @Override
    public int regionSize() {
        return child.regionSize();
    }

    @Override
    protected SudokuDivider divider() {
        return new SubSudokuDivider(child.divider(),
                areaStartingIn(upperLeftCorner()),
                areaStartingIn(upperRightCorner()),
                areaStartingIn(center()),
                areaStartingIn(lowerLeftCorner()),
                areaStartingIn(lowerRightCorner()));
    }

    private Point upperLeftCorner() {
        return new Point(0, 0);
    }

    private Point upperRightCorner() {
        return new Point(this.width() - child.width(), 0);
    }

    private Point center() {
        int x = (this.width() - child.width()) / 2;
        int y = (this.height() - child.height()) / 2;
        return new Point(x, y);
    }

    private Point lowerLeftCorner() {
        return new Point(0, this.height() - child.height());
    }

    private Point lowerRightCorner() {
        return new Point(this.width() - child.width(), this.height() - child.height());
    }

    private Area areaStartingIn(Point p) {
        return new Area(p, new Point(p.x() + child.width(), p.y() + child.height()));
    }

    @Override
    public Sudoku template() {
        Sudoku sudoku = new Sudoku(width(), height());
        sudoku.append(child.template(), upperLeftCorner());
        sudoku.append(child.template(), upperRightCorner());
        sudoku.append(child.template(), center());
        sudoku.append(child.template(), lowerLeftCorner());
        sudoku.append(child.template(), lowerRightCorner());
        return sudoku;
    }

}
