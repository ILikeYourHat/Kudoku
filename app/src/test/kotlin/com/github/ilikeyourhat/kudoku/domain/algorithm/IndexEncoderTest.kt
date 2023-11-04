package com.github.ilikeyourhat.kudoku.domain.algorithm

import com.github.ilikeyourhat.kudoku.model.Point
import com.github.ilikeyourhat.kudoku.solving.sat.IndexEncoder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
