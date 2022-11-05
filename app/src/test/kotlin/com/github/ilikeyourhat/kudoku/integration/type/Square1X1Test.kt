package com.github.ilikeyourhat.kudoku.integration.type

class Square1X1Test: SudokuTypesTest() {

    override val puzzle = """
            classic_1x1
            _
        """.trimIndent()

    override val solution = """
            classic_1x1
            1
        """.trimIndent()
}