package pl.laskowski.marcin.domain.algorithm

import org.junit.Assert.*
import org.junit.Test
import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.solving.sat.IndexEncoder

class IndexEncoderTest {
    @Test
    fun check() {
        val encoder = IndexEncoder(30, 30)
        val p = Point(0, 17)
        val value = 3
        val index = encoder.encode(p, value)
        assertEquals(p, encoder.decodePoint(index))
        assertEquals(value.toLong(), encoder.decodeValue(index).toLong())
    }
}