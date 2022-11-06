package com.github.ilikeyourhat.kudoku.integration.type

class ClassicVertical6x6Test: SudokuTypesTest() {

    override val puzzle = """
            classic_vertical_6x6
            4,_, _,_, 6,_
            _,6, _,4, 1,5
            _,_, 1,_, _,_
            
            _,_, _,_, _,_
            _,_, _,_, 5,3
            5,_, _,3, _,_
        """.trimIndent()

    override val solution = """
            classic_vertical_6x6
            4,1, 3,5, 6,2
            3,6, 2,4, 1,5
            2,5, 1,6, 3,4
            
            1,3, 5,2, 4,6
            6,2, 4,1, 5,3
            5,4, 6,3, 2,1
        """.trimIndent()
}