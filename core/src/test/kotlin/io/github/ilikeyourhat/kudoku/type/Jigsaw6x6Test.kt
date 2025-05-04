package io.github.ilikeyourhat.kudoku.type

class Jigsaw6x6Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        jigsaw_6x6
        _,_,1,_,_,_
        _,_,_,_,_,6
        _,_,2,_,_,5
        _,1,_,_,3,_
        _,_,5,_,_,_
        _,_,_,_,_,_
        
        3,5,5,5,5,1
        3,3,3,6,5,1
        3,2,3,6,5,1
        2,2,2,6,1,1
        2,6,6,6,1,4
        2,4,4,4,4,4
    """.trimIndent()

    override val solution = """
        jigsaw_6x6
        6,3,1,5,4,2
        4,5,3,1,2,6
        1,4,2,3,6,5
        5,1,6,2,3,4
        2,6,5,4,1,3
        3,2,4,6,5,1
        
        3,5,5,5,5,1
        3,3,3,6,5,1
        3,2,3,6,5,1
        2,2,2,6,1,1
        2,6,6,6,1,4
        2,4,4,4,4,4
    """.trimIndent()
}
