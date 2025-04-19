package io.github.ilikeyourhat.kudoku.type

class Jigsaw4x4Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        jigsaw_4x4
        _,_,4,_
        _,3,_,_
        _,2,_,_
        _,_,_,_
        
        1,1,1,1
        2,2,2,4
        3,2,4,4
        3,3,3,4
    """.trimIndent()

    override val solution = """
        jigsaw_4x4
        2,1,4,3
        4,3,1,2
        1,2,3,4
        3,4,2,1
        
        1,1,1,1
        2,2,2,4
        3,2,4,4
        3,3,3,4
    """.trimIndent()
}
