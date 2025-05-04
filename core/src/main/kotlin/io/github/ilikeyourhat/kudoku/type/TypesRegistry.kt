package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType

object TypesRegistry {

    private val buildInTypes = listOf(
        Butterfly12x12,
        Classic4x4,
        Classic6x6,
        Classic6x6Vertical,
        Classic9x9,
        Classic12x12,
        Classic12x12Vertical,
        Classic16x16,
        Classic25x25,
        Classic36x36,
        DoubleBackslash15x15,
        DoubleDiagonal9x9,
        DoubleSlash15x15,
        Jigsaw4x4,
        Jigsaw5x5,
        Jigsaw6x6,
        Jigsaw7x7,
        Jigsaw8x8,
        Jigsaw9x9,
        Jigsaw16x16,
        SamuraiButterfly30x30,
        SamuraiClassic21x21,
        SamuraiClassic40x40,
        Square1x1,
        Square2x2,
        TripleBackslash15x15,
        TripleSlash15x15
    )

    private val typeMap = buildInTypes
        .associateBy { it.name }
        .toMutableMap()

    fun register(type: SudokuType) {
        typeMap[type.name] = type
    }

    fun getTypeByName(name: String): SudokuType? {
        return typeMap[name]
    }
}

val CLASSIC_TYPES = listOf(
    Classic4x4,
    Classic9x9,
    Classic16x16,
    Classic25x25,
    Classic36x36
)
