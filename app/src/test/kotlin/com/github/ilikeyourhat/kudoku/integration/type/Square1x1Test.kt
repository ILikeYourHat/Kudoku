package com.github.ilikeyourhat.kudoku.integration.type

class Square1x1Test: SudokuTypesTest() {

    override val puzzle = """
            square_1x1
            _
        """.trimIndent()

    override val solution = """
            square_1x1
            1
        """.trimIndent()
}
