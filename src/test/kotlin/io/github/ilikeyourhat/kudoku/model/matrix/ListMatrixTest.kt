package io.github.ilikeyourhat.kudoku.model.matrix

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.collections.shouldContainOnlyNulls
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldHaveSameHashCodeAs
import io.kotest.matchers.types.shouldNotHaveSameHashCodeAs
import org.junit.jupiter.api.Test

class ListMatrixTest {

    @Test
    fun `create empty matrix and check its size`() {
        val matrix = ListMatrix(0, 0, null)

        matrix.sizeX.shouldBeEqual(0)
        matrix.sizeY.shouldBeEqual(0)
        matrix.shouldBeEmpty()
    }

    @Test
    fun `create non empty matrix and check its size`() {
        val matrix = ListMatrix(4, 7, null)

        matrix.sizeX.shouldBeEqual(4)
        matrix.sizeY.shouldBeEqual(7)
        matrix.shouldNotBeEmpty()
    }

    @Test
    fun `check if matrix is initialized with nulls`() {
        val matrix: ListMatrix<Any?> = ListMatrix(2, 2, null)

        matrix.shouldContainOnlyNulls()
    }

    @Test
    fun `check if matrix is initialized with default value`() {
        val matrix = ListMatrix(2, 2, "test")

        matrix.shouldContainOnly("test")
    }

    @Test
    fun `insert object into matrix and check it's there`() {
        val matrix = ListMatrix<Any?>(3, 3, null)
        val any = Any()

        matrix[1, 2] = any

        matrix[1, 2].shouldBe(any)
    }

    @Test
    fun `check if matrix contains given element`() {
        val matrix = ListMatrix(2, 2, 0)

        matrix.contains(7)
            .shouldBeFalse()

        matrix[1, 1] = 7

        matrix.contains(7)
            .shouldBeTrue()
    }

    @Test
    fun `check if matrix contains all of the given elements`() {
        val matrix = ListMatrix(2, 2, 0)

        matrix.containsAll(listOf(7, 8))
            .shouldBeFalse()

        matrix[1, 1] = 7
        matrix[0, 1] = 8

        matrix.containsAll(listOf(7, 8))
            .shouldBeTrue()
    }

    @Test
    fun `check basic equals contract`() {
        val matrix = ListMatrix(2, 2, null)
        val sameMatrix = ListMatrix(2, 2, null)

        matrix.shouldBeEqual(sameMatrix)
        matrix.shouldHaveSameHashCodeAs(sameMatrix)
        matrix.shouldNotBeEqual("a string")
    }

    @Test
    fun `check if matrix is equal when size is equal`() {
        val matrix = ListMatrix(2, 2, null)
        val equalMatrix = ListMatrix(2, 2, null)
        val differentMatrix = ListMatrix(1000, 1000, null)
        val matrixWithDifferentSizeY = ListMatrix(2, 3, null)

        matrix.shouldBeEqual(equalMatrix)
        matrix.shouldNotBeEqual(differentMatrix)
        matrix.shouldNotBeEqual(matrixWithDifferentSizeY)

        matrix.shouldHaveSameHashCodeAs(equalMatrix)
        matrix.shouldNotHaveSameHashCodeAs(differentMatrix)
        matrix.shouldNotHaveSameHashCodeAs(matrixWithDifferentSizeY)
    }

    @Test
    fun `check if matrix is equal when data is equal`() {
        val twinMatrix1 = ListMatrix(2, 2, "")
        twinMatrix1[0, 0] = "twin"

        val twinMatrix2 = ListMatrix(2, 2, "")
        twinMatrix2[0, 0] = "twin"

        val differentMatrix1 = ListMatrix(2, 2, "")
        differentMatrix1[0, 0] = "not twin"

        val differentMatrix2 = ListMatrix(2, 2, "")
        differentMatrix2[1, 1] = "twin"

        twinMatrix1.shouldBeEqual(twinMatrix2)
        twinMatrix1.shouldNotBeEqual(differentMatrix1)
        twinMatrix1.shouldNotBeEqual(differentMatrix2)

        twinMatrix1.shouldHaveSameHashCodeAs(twinMatrix2)
        twinMatrix1.shouldNotHaveSameHashCodeAs(differentMatrix1)
        twinMatrix1.shouldNotHaveSameHashCodeAs(differentMatrix2)
    }

    @Test
    @Suppress("ArgumentListWrapping") // for better coordinates readability
    fun `check if coordinates by index works`() {
        val matrix = ListMatrix(3, 2, "")

        val coordinates = (0 until matrix.size).toList()
            .map { index -> matrix.coordinatesOf(index) }

        coordinates.shouldBeEqual(
            listOf(
                0 to 0, 1 to 0, 2 to 0,
                0 to 1, 1 to 1, 2 to 1
            )
        )
    }

    @Test
    fun `should throw exception when creating matrix with negative size`() {
        shouldThrow<IllegalArgumentException> { ListMatrix(-1, 0, "") }
            .shouldHaveMessage("sizeX must be non-negative")
        shouldThrow<IllegalArgumentException> { ListMatrix(0, -1, "") }
            .shouldHaveMessage("sizeY must be non-negative")
    }

    @Test
    fun `should throw exception when creating matrix with incorrect data size`() {
        shouldThrow<IllegalArgumentException> { ListMatrix(2, 2, listOf(1, 2)) }
            .shouldHaveMessage("Data size must be equal to 4, but was 2")
        shouldThrow<IllegalArgumentException> { ListMatrix(2, 2, listOf(1, 2, 3, 4, 5)) }
            .shouldHaveMessage("Data size must be equal to 4, but was 5")
    }

    @Test
    fun `should throw exception when coordinates are out of bounds`() {
        val matrix = ListMatrix(3, 2, "")

        shouldThrow<IndexOutOfBoundsException> { matrix[3, 0] }
            .shouldHaveMessage("x must be in range [0, 2], but was 3")
        shouldThrow<IndexOutOfBoundsException> { matrix[0, 3] }
            .shouldHaveMessage("y must be in range [0, 1], but was 3")

        shouldThrow<IndexOutOfBoundsException> { matrix[3, 0] = "test" }
            .shouldHaveMessage("x must be in range [0, 2], but was 3")
        shouldThrow<IndexOutOfBoundsException> { matrix[0, 3] = "test" }
            .shouldHaveMessage("y must be in range [0, 1], but was 3")
    }
}
