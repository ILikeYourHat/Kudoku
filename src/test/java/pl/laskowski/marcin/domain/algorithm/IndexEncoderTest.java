package pl.laskowski.marcin.domain.algorithm;

import org.junit.Assert;
import org.junit.Test;
import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.solving.sat.IndexEncoder;

/**
 * Created by Marcin Laskowski.
 */

public class IndexEncoderTest {

    @Test
    public void check() {
        IndexEncoder encoder = new IndexEncoder(30, 30);
        Point p = new Point(0, 17);
        int value = 3;
        int index = encoder.encode(p, value);
        Assert.assertEquals(p, encoder.decodePoint(index));
        Assert.assertEquals(value, encoder.decodeValue(index));
    }
}
