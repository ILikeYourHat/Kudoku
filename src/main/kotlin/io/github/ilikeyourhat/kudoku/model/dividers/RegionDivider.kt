package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.generating.RandomRegionGenerator
import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Field
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.SudokuType
import kotlin.random.Random

class RegionDivider {

    private val dividers = mutableListOf<DivideCommand>()

    fun divideByRows() = apply {
        dividers.add { _, board ->
            (0 until board.sizeY()).map { indexY ->
                board.region(0, indexY, board.sizeX() - 1, indexY)
            }
        }
    }

    fun divideByColumns() = apply {
        dividers.add { _, board ->
            (0 until board.sizeX()).map { indexX ->
                board.region(indexX, 0, indexX, board.sizeY() - 1)
            }
        }
    }

    fun divideByBlocks(blockSize: Int) = divideByBlocks(blockSize, blockSize)

    fun divideByBlocks(blockSizeX: Int, blockSizeY: Int) = apply {
        dividers.add { _, board ->
            require(board.sizeX() % blockSizeX == 0) { "blockSizeX is $blockSizeX, sizeX is ${board.sizeX()}" }
            require(board.sizeY() % blockSizeY == 0) { "blockSizeY is $blockSizeY, sizeY is ${board.sizeY()}" }

            val regions = mutableListOf<Region>()
            for (x in 0 until board.sizeX() step blockSizeX) {
                for (y in 0 until board.sizeY() step blockSizeY) {
                    regions += board.region(x, y, x + blockSizeX - 1, y + blockSizeY - 1)
                }
            }
            regions
        }
    }

    fun divideRandomly(random: Random = Random) = apply {
        dividers.add { type, board ->
            val regionGenerator = RandomRegionGenerator(random)
            regionGenerator.generateRandomRegions(type, board)
        }
    }

    fun divideByRegionId(regionIds: List<Int>) = apply {
        dividers.add { _, board ->
            val fields = board.fields()
            require(fields.size == regionIds.size)
            regionIds.zip(fields)
                .groupBy({ it.first }, { it.second })
                .map { Region(it.value) }
        }
    }

    fun allFields() = apply {
        dividers.add { _, board ->
            listOf(board.region(0, 0, board.sizeX() - 1, board.sizeY() - 1))
        }
    }

    fun primaryDiagonal() = apply {
        dividers.add { _, board ->
            require(board.sizeX() == board.sizeY())
            val fields = mutableListOf<Field>()
            for (x in 0 until board.sizeX()) {
                fields += board.get(x, x)
            }
            listOf(Region(fields))
        }
    }

    fun antiDiagonal() = apply {
        dividers.add { _, board ->
            require(board.sizeX() == board.sizeY())
            val fields = mutableListOf<Field>()
            for (x in 0 until board.sizeX()) {
                fields += board.get(x, board.sizeY() - x - 1)
            }
            listOf(Region(fields))
        }
    }

    fun applySubSudoku(x: Int, y: Int, type: SudokuType) = apply {
        dividers.add { _, board ->
            val subBoard = board.fragment(x, y, x + type.sizeX, y + type.sizeY)
            type.divider().divide(type, subBoard)
        }
    }

    fun divide(type: SudokuType, board: Board): List<Region> {
        return dividers
            .flatMap { it.divide(type, board) }
            .distinct()
    }
}

fun interface DivideCommand {
    fun divide(type: SudokuType, board: Board): List<Region>
}
