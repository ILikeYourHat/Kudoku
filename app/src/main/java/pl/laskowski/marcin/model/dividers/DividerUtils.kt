package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region

fun Board.divideByRows(): List<Region> {
     return (0 until sizeY()).map { indexY ->
         region(0, indexY, sizeX() - 1, indexY)
     }
}

fun Board.divideByColumns(): List<Region> {
    return (0 until sizeX()).map { indexX ->
        region(indexX, 0, indexX, sizeY() - 1)
    }
}

fun Board.divideByBlocks(blockSizeX: Int, blockSizeY: Int): List<Region> {
    require(sizeX() % blockSizeX == 0) { "blockSizeX is $blockSizeX, sizeX is ${sizeX()}" }
    require(sizeY() % blockSizeY == 0) { "blockSizeY is $blockSizeY, sizeY is ${sizeY()}" }

    val regions = mutableListOf<Region>()
    for (x in 0 until sizeX() step blockSizeX) {
        for (y in 0 until sizeY() step blockSizeY) {
            regions += region(x, y, x + blockSizeX - 1, y + blockSizeY - 1)
        }
    }
    return regions
}

fun Board.primaryDiagonal(): List<Region> {
    require(sizeX() == sizeY())

    val fields = mutableListOf<Field>()
    for (x in 0 until sizeX()) {
        fields += at(x, x)!!
    }
    return listOf(Region(fields))
}


fun Board.antiDiagonal(): List<Region> {
    require(sizeX() == sizeY())

    val fields = mutableListOf<Field>()
    for (x in 0 until sizeX()) {
        fields += at(x, sizeY() - x - 1)!!
    }
    return listOf(Region(fields))
}

fun Board.allFields(): List<Region> {
    return listOf(Region(fields().filterNotNull()))
}