package io.github.ilikeyourhat.kudoku.solving.sat

import io.github.ilikeyourhat.kudoku.model.Point
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.lang.IllegalArgumentException

class IndexEncoderTest {

    @ParameterizedTest
    @Suppress("LongParameterList")
    @CsvSource(
        value = [
            "  30,   30,    9,   0,  17,    3,       2311",
            "  10,   20,    9,   9,  18,    1,        190",
            "  20,   10,    9,  18,   9,    1,        199",
            "   1,    1,    1,   0,   0,    1,          1",
            " 100,  100,  100,  99,  99,  100,    1000000",
            "1000, 1000, 1000, 999, 999, 1000, 1000000000"
        ]
    )
    fun `should encode and decode valid values`(
        sizeX: Int,
        sizeY: Int,
        maxValue: Int,
        pointX: Int,
        pointY: Int,
        pointValue: Int,
        encodedValue: Int
    ) {
        val encoder = IndexEncoder(sizeX, sizeY, maxValue)

        val index = encoder.encode(Point(pointX, pointY), pointValue)

        index shouldBe encodedValue
        encoder.decodePoint(index) shouldBe Point(pointX, pointY)
        encoder.decodeValue(index) shouldBe pointValue
    }

    @Test
    fun `should validate sizeX parameter during construction`() {
        shouldThrow<IllegalArgumentException> {
            IndexEncoder(0, 10, 10)
        }.shouldHaveMessage("sizeX must be greater than 0, got 0")
    }

    @Test
    fun `should validate sizeY parameter during construction`() {
        shouldThrow<IllegalArgumentException> {
            IndexEncoder(10, 0, 10)
        }.shouldHaveMessage("sizeY must be greater than 0, got 0")
    }

    @Test
    fun `should validate maxValue parameter during construction`() {
        shouldThrow<IllegalArgumentException> {
            IndexEncoder(10, 10, 0)
        }.shouldHaveMessage("maxValue must be greater than 0, got 0")
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "       101, 1000000002, 1000000003",
            "1000000001,        102, 1000000003",
            "1000000001, 1000000002,        103",
            "1000000001, 1000000002, 1000000003"
        ]
    )
    fun `maximum possible index value should fit inside Int`(
        sizeX: Int,
        sizeY: Int,
        maxValue: Int
    ) {
        shouldThrow<IllegalArgumentException> {
            IndexEncoder(sizeX, sizeY, maxValue)
        }.shouldHaveMessage("Possible Int overflow for given values: sizeX=$sizeX, sizeY=$sizeY, maxValue=$maxValue")
    }
}
