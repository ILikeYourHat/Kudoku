package pl.laskowski.marcin.model

/**
 * Created by Marcin Laskowski.
 */
data class Point(
    val x: Int,
    val y: Int
) {

    fun x() = x

    fun y() = y

    fun interface Mapper {
        fun map(p: Point): Point
    }
}