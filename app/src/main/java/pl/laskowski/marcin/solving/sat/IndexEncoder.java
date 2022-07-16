package pl.laskowski.marcin.solving.sat;

import pl.laskowski.marcin.model.Point;

/**
 * Created by Marcin Laskowski.
 */

public class IndexEncoder {

    private final int sizeX;
    private final int sizeY;
    private final int blockSize;

    public IndexEncoder(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.blockSize = sizeX * sizeY;
    }

    public int encode(Point p, int value) {
        return p.getX() + sizeX * p.getY() + blockSize * --value + 1;
    }

    public Point decodePoint(int index) {
        int x = (index - 1) % sizeX;
        int y = ((index - 1) / sizeX) % sizeY;
        return new Point(x, y);
    }

    public int decodeValue(int index) {
        return --index / blockSize + 1;
    }

}
