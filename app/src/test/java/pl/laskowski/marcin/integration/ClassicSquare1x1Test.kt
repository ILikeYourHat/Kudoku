package pl.laskowski.marcin.integration

class ClassicSquare1x1Test: SudokuTypesTest() {

    override val puzzle = """
            classic_1x1
            _
        """.trimIndent()

    override val solution = """
            classic_1x1
            1
        """.trimIndent()
}