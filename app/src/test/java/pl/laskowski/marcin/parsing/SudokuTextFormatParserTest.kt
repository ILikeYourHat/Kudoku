package pl.laskowski.marcin.parsing

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.parsing.text.SudokuTextFormatParser


class SudokuTextFormatParserTest {

    @Rule
    @JvmField
    val folder = TemporaryFolder()

    @Test
    fun `parse one sudoku from text`() {
        val text = """
            9 9
            _,_,3 ,_,2,_, 6,_,_
            9,_,_ ,3,_,5, _,_,1
            _,_,1 ,8,_,6, 4,_,_

            _,_,8, 1,_,2, 9,_,_
            7,_,_, _,_,_, _,_,8
            _,_,6, 7,_,8, 2,_,_

            _,_,2, 6,_,9, 5,_,_
            8,_,_, 2,_,3, _,_,9
            _,_,5, _,1,_, 3,_,_
        """.trimIndent()

        val sudoku = SudokuTextFormatParser().parseOne(text)

        assertEquals(
            Sudoku(
                9, 9, arrayOf(
                    0, 0, 3, 0, 2, 0, 6, 0, 0,
                    9, 0, 0, 3, 0, 5, 0, 0, 1,
                    0, 0, 1, 8, 0, 6, 4, 0, 0,
                    0, 0, 8, 1, 0, 2, 9, 0, 0,
                    7, 0, 0, 0, 0, 0, 0, 0, 8,
                    0, 0, 6, 7, 0, 8, 2, 0, 0,
                    0, 0, 2, 6, 0, 9, 5, 0, 0,
                    8, 0, 0, 2, 0, 3, 0, 0, 9,
                    0, 0, 5, 0, 1, 0, 3, 0, 0
                )
            ), sudoku
        )
    }

    @Test
    fun `parse one sudoku from file`() {
        val file = folder.newFile("test.txt")
        file.writeText(
            """
            9 9
            _,_,3 ,_,2,_, 6,_,_
            9,_,_ ,3,_,5, _,_,1
            _,_,1 ,8,_,6, 4,_,_

            _,_,8, 1,_,2, 9,_,_
            7,_,_, _,_,_, _,_,8
            _,_,6, 7,_,8, 2,_,_

            _,_,2, 6,_,9, 5,_,_
            8,_,_, 2,_,3, _,_,9
            _,_,5, _,1,_, 3,_,_
        """.trimIndent()
        )

        val sudoku = SudokuTextFormatParser().parseOne(file)

        assertEquals(
            Sudoku(
                9, 9, arrayOf(
                    0, 0, 3, 0, 2, 0, 6, 0, 0,
                    9, 0, 0, 3, 0, 5, 0, 0, 1,
                    0, 0, 1, 8, 0, 6, 4, 0, 0,
                    0, 0, 8, 1, 0, 2, 9, 0, 0,
                    7, 0, 0, 0, 0, 0, 0, 0, 8,
                    0, 0, 6, 7, 0, 8, 2, 0, 0,
                    0, 0, 2, 6, 0, 9, 5, 0, 0,
                    8, 0, 0, 2, 0, 3, 0, 0, 9,
                    0, 0, 5, 0, 1, 0, 3, 0, 0
                )
            ), sudoku
        )
    }

    @Test
    fun `parse many sudoku from text`() {
        val text = """
            9 9
            _,_,3 ,_,2,_, 6,_,_
            9,_,_ ,3,_,5, _,_,1
            _,_,1 ,8,_,6, 4,_,_

            _,_,8, 1,_,2, 9,_,_
            7,_,_, _,_,_, _,_,8
            _,_,6, 7,_,8, 2,_,_

            _,_,2, 6,_,9, 5,_,_
            8,_,_, 2,_,3, _,_,9
            _,_,5, _,1,_, 3,_,_
            
            9 9
            2,_,_, _,8,_, 3,_,_
            _,6,_, _,7,_, _,8,4
            _,3,_, 5,_,_, 2,_,9

            _,_,_, 1,_,5, 4,_,8
            _,_,_, _,_,_, _,_,_
            4,_,2, 7,_,6, _,_,_
            
            3,_,1, _,_,7, _,4,_
            7,2,_, _,4,_, _,6,_
            _,_,4, _,1,_, _,_,3
        """.trimIndent()

        val sudoku = SudokuTextFormatParser().parseMany(text)

        assertEquals(
            listOf(
                Sudoku(
                    9, 9, arrayOf(
                        0, 0, 3, 0, 2, 0, 6, 0, 0,
                        9, 0, 0, 3, 0, 5, 0, 0, 1,
                        0, 0, 1, 8, 0, 6, 4, 0, 0,
                        0, 0, 8, 1, 0, 2, 9, 0, 0,
                        7, 0, 0, 0, 0, 0, 0, 0, 8,
                        0, 0, 6, 7, 0, 8, 2, 0, 0,
                        0, 0, 2, 6, 0, 9, 5, 0, 0,
                        8, 0, 0, 2, 0, 3, 0, 0, 9,
                        0, 0, 5, 0, 1, 0, 3, 0, 0
                    )
                ),
                Sudoku(
                    9, 9,
                    arrayOf(
                        2, 0, 0, 0, 8, 0, 3, 0, 0,
                        0, 6, 0, 0, 7, 0, 0, 8, 4,
                        0, 3, 0, 5, 0, 0, 2, 0, 9,

                        0, 0, 0, 1, 0, 5, 4, 0, 8,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        4, 0, 2, 7, 0, 6, 0, 0, 0,

                        3, 0, 1, 0, 0, 7, 0, 4, 0,
                        7, 2, 0, 0, 4, 0, 0, 6, 0,
                        0, 0, 4, 0, 1, 0, 0, 0, 3
                    ),
                )
            ), sudoku
        )
    }

    @Test
    fun `parse many sudoku from file`() {
        val file = folder.newFile("test.txt")
        file.writeText(
            """
            9 9
            _,_,3 ,_,2,_, 6,_,_
            9,_,_ ,3,_,5, _,_,1
            _,_,1 ,8,_,6, 4,_,_

            _,_,8, 1,_,2, 9,_,_
            7,_,_, _,_,_, _,_,8
            _,_,6, 7,_,8, 2,_,_

            _,_,2, 6,_,9, 5,_,_
            8,_,_, 2,_,3, _,_,9
            _,_,5, _,1,_, 3,_,_
            
            9 9
            2,_,_, _,8,_, 3,_,_
            _,6,_, _,7,_, _,8,4
            _,3,_, 5,_,_, 2,_,9

            _,_,_, 1,_,5, 4,_,8
            _,_,_, _,_,_, _,_,_
            4,_,2, 7,_,6, _,_,_
            
            3,_,1, _,_,7, _,4,_
            7,2,_, _,4,_, _,6,_
            _,_,4, _,1,_, _,_,3
        """.trimIndent()
        )

        val sudoku = SudokuTextFormatParser().parseMany(file)

        assertEquals(
            listOf(
                Sudoku(
                    9, 9, arrayOf(
                        0, 0, 3, 0, 2, 0, 6, 0, 0,
                        9, 0, 0, 3, 0, 5, 0, 0, 1,
                        0, 0, 1, 8, 0, 6, 4, 0, 0,
                        0, 0, 8, 1, 0, 2, 9, 0, 0,
                        7, 0, 0, 0, 0, 0, 0, 0, 8,
                        0, 0, 6, 7, 0, 8, 2, 0, 0,
                        0, 0, 2, 6, 0, 9, 5, 0, 0,
                        8, 0, 0, 2, 0, 3, 0, 0, 9,
                        0, 0, 5, 0, 1, 0, 3, 0, 0
                    )
                ),
                Sudoku(
                    9, 9,
                    arrayOf(
                        2, 0, 0, 0, 8, 0, 3, 0, 0,
                        0, 6, 0, 0, 7, 0, 0, 8, 4,
                        0, 3, 0, 5, 0, 0, 2, 0, 9,

                        0, 0, 0, 1, 0, 5, 4, 0, 8,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        4, 0, 2, 7, 0, 6, 0, 0, 0,

                        3, 0, 1, 0, 0, 7, 0, 4, 0,
                        7, 2, 0, 0, 4, 0, 0, 6, 0,
                        0, 0, 4, 0, 1, 0, 0, 0, 3
                    ),
                )
            ), sudoku
        )
    }
}