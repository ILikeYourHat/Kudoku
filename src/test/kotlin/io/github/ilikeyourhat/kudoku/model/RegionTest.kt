package io.github.ilikeyourhat.kudoku.model

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class RegionTest {

    @Test
    fun `should create region from fields`() {
        val field1 = Field(0, 0)
        val field2 = Field(0, 1)
        val field3 = Field(1, 0)
        val field4 = Field(1, 1)

        val region = Region(listOf(field1, field2, field3, field4))

        assert(region.size() == 4)
        assert(region.fields.contains(field1))
        assert(region.fields.contains(field2))
        assert(region.fields.contains(field3))
        assert(region.fields.contains(field4))
    }

    @Test
    fun `should check if region is valid`() {
        val field1 = Field(0, 0)
        val field2 = Field(0, 1)
        val field3 = Field(1, 0)
        val field4 = Field(1, 1)

        val region = Region(listOf(field1, field2, field3, field4))

        assert(region.isValid())
    }

    @Test
    fun `should check equals contract`() {
        val field1 = Field(0, 0)
        val field2 = Field(0, 1)
        val field3 = Field(1, 0)
        val field4 = Field(1, 1)

        val field5 = Field(0, 0)
        val field6 = Field(0, 1)
        val field7 = Field(1, 0)
        val field8 = Field(1, 1)

        val region1 = Region(listOf(field1, field2, field3, field4))
        val region2 = Region(listOf(field5, field6, field7, field8))

        region1.shouldBeEqual(region2)
    }
}
