package com.github.ilikeyourhat.kudoku.integration.type

class SamuraiClassic21x21Test: SudokuTypesTest() {

    override val puzzle = """
            samurai_classic_21x21
            2,_,_, _,_,8, 9,_,_, #,#,#, 9,_,_, _,3,1, _,7,8
            _,_,7, _,9,_, _,_,6, #,#,#, _,3,_, _,_,5, _,_,_
            3,_,_, 5,_,_, 2,_,_, #,#,#, 4,_,_, _,_,_, _,_,3
            
            _,_,_, 8,_,_, 6,_,_, #,#,#, _,_,_, _,_,2, _,_,9
            5,_,9, 1,_,_, _,_,_, #,#,#, _,7,6, _,_,_, 8,_,_
            1,_,_, _,_,6, _,5,_, #,#,#, _,_,_, _,_,_, _,_,_
            
            _,4,5, _,_,_, 3,_,_, _,_,_, _,_,7, _,_,3, _,1,_
            _,1,_, _,_,_, 4,_,_, _,2,3, _,_,5, 4,_,_, _,3,_
            6,_,_, _,_,_, _,_,_, _,7,_, _,_,_, _,_,6, 5,_,_
            
            #,#,#, #,#,#, _,8,_, _,_,_, _,_,2, #,#,#, #,#,#
            #,#,#, #,#,#, _,_,_, _,_,1, _,_,_, #,#,#, #,#,#
            #,#,#, #,#,#, _,_,_, _,_,4, _,7,_, #,#,#, #,#,#
            
            _,2,_, _,_,_, _,_,_, _,_,_, _,_,1, 7,_,_, _,_,_
            _,_,_, _,_,_, _,_,_, _,_,_, _,_,_, _,2,9, _,5,_
            9,5,_, 7,_,_, _,_,_, _,5,_, _,8,_, _,_,3, _,_,_
            
            _,_,_, _,_,1, _,_,_, #,#,#, _,_,_, 9,_,_, _,_,_
            3,4,_, _,7,_, _,_,_, #,#,#, _,_,_, _,_,_, 4,6,_
            6,_,_, _,3,_, 7,_,_, #,#,#, _,9,_, _,1,_, _,8,_
            
            _,_,_, 2,5,8, _,_,4, #,#,#, 4,2,3, _,_,_, _,_,_
            _,_,3, 6,_,_, _,5,_, #,#,#, _,6,_, _,5,_, 2,_,_
            4,_,_, _,_,_, 9,_,_, #,#,#, _,_,_, 3,_,_, 9,_,7
        """.trimIndent()

    override val solution = """
            samurai_classic_21x21
            2,5,1, 6,4,8, 9,7,3, #,#,#, 9,5,2, 6,3,1, 4,7,8
            4,8,7, 3,9,2, 5,1,6, #,#,#, 7,3,1, 8,4,5, 6,9,2
            3,9,6, 5,1,7, 2,8,4, #,#,#, 4,6,8, 2,7,9, 1,5,3
            
            7,2,4, 8,5,9, 6,3,1, #,#,#, 5,8,3, 1,6,2, 7,4,9
            5,6,9, 1,7,3, 8,4,2, #,#,#, 1,7,6, 3,9,4, 8,2,5
            1,3,8, 4,2,6, 7,5,9, #,#,#, 2,4,9, 7,5,8, 3,6,1
            
            9,4,5, 7,6,1, 3,2,8, 1,4,5, 6,9,7, 5,8,3, 2,1,4
            8,1,2, 9,3,5, 4,6,7, 9,2,3, 8,1,5, 4,2,7, 9,3,6
            6,7,3, 2,8,4, 1,9,5, 6,7,8, 3,2,4, 9,1,6, 5,8,7
            
            #,#,#, #,#,#, 9,8,1, 7,3,6, 4,5,2, #,#,#, #,#,#
            #,#,#, #,#,#, 7,5,4, 2,8,1, 9,6,3, #,#,#, #,#,#
            #,#,#, #,#,#, 2,3,6, 5,9,4, 1,7,8, #,#,#, #,#,#
            
            1,2,6, 5,4,3, 8,7,9, 3,6,2, 5,4,1, 7,8,6, 3,9,2
            8,3,7, 1,6,9, 5,4,2, 8,1,9, 7,3,6, 1,2,9, 8,5,4
            9,5,4, 7,8,2, 6,1,3, 4,5,7, 2,8,9, 5,4,3, 6,7,1
            
            5,7,8, 9,2,1, 4,3,6, #,#,#, 6,5,4, 9,7,8, 1,2,3
            3,4,1, 8,7,6, 2,9,5, #,#,#, 1,7,8, 2,3,5, 4,6,9
            6,9,2, 4,3,5, 7,8,1, #,#,#, 3,9,2, 6,1,4, 7,8,5
            
            7,1,9, 2,5,8, 3,6,4, #,#,#, 4,2,3, 8,9,7, 5,1,6
            2,8,3, 6,9,4, 1,5,7, #,#,#, 9,6,7, 4,5,1, 2,3,8
            4,6,5, 3,1,7, 9,2,8, #,#,#, 8,1,5, 3,6,2, 9,4,7
        """.trimIndent()
}