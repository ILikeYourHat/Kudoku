package com.github.ilikeyourhat.kudoku.parsing

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.type.Classic9x9
import com.github.ilikeyourhat.kudoku.parsing.text.SudokuTextFormatParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class SudokuTextFormatParserTest {

    @field:TempDir
    lateinit var tempDir: File

    @Test
    fun `parse one sudoku from text`() {
        val text = """
            classic_9x9
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
                Classic9x9, listOf(
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
        val file = File(tempDir, "test.txt")
        file.writeText(
            """
            classic_9x9
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
                Classic9x9, listOf(
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
            classic_9x9
            _,_,3 ,_,2,_, 6,_,_
            9,_,_ ,3,_,5, _,_,1
            _,_,1 ,8,_,6, 4,_,_

            _,_,8, 1,_,2, 9,_,_
            7,_,_, _,_,_, _,_,8
            _,_,6, 7,_,8, 2,_,_

            _,_,2, 6,_,9, 5,_,_
            8,_,_, 2,_,3, _,_,9
            _,_,5, _,1,_, 3,_,_
            
            classic_9x9
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
                    Classic9x9, listOf(
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
                    Classic9x9, listOf(
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
        val file = File(tempDir, "test.txt")
        file.writeText(
            """
            classic_9x9
            _,_,3 ,_,2,_, 6,_,_
            9,_,_ ,3,_,5, _,_,1
            _,_,1 ,8,_,6, 4,_,_

            _,_,8, 1,_,2, 9,_,_
            7,_,_, _,_,_, _,_,8
            _,_,6, 7,_,8, 2,_,_

            _,_,2, 6,_,9, 5,_,_
            8,_,_, 2,_,3, _,_,9
            _,_,5, _,1,_, 3,_,_
            
            classic_9x9
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
                    Classic9x9, listOf(
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
                    Classic9x9, listOf(
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
