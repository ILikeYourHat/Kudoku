package io.github.ilikeyourhat.kudoku.type

class Jigsaw5x5Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        jigsaw_5x5
        _,_,_,_,_
        _,_,_,_,2
        _,_,_,3,5
        _,1,_,_,_
        _,_,3,_,_
        
        1,1,1,5,5
        1,1,5,5,5
        2,2,2,4,4
        2,3,3,4,4
        2,3,3,3,4
    """.trimIndent()

    override val solution = """
        jigsaw_5x5
        4,5,2,1,3
        1,3,4,5,2
        2,4,1,3,5
        3,1,5,2,4
        5,2,3,4,1
        
        1,1,1,5,5
        1,1,5,5,5
        2,2,2,4,4
        2,3,3,4,4
        2,3,3,3,4
    """.trimIndent()
}
