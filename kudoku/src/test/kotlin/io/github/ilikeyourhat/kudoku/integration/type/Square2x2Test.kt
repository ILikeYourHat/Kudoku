package io.github.ilikeyourhat.kudoku.integration.type

class Square2x2Test: SudokuTypesTest() {

    override val puzzle = """
            square_2x2
            2,1
            _,_
        """.trimIndent()

    override val solution = """
            square_2x2
            2,1
            1,2
        """.trimIndent()
}
