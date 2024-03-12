package io.github.ilikeyourhat.kudoku.model

data class Region(
    val fields: Set<Field>
): Iterable<Field> {

    constructor(fields: List<Field>): this(fields.toSet())

    override fun iterator() = fields.iterator()

    fun size() = fields.size

    fun isEmpty() = fields.isEmpty()

    fun intersect(other: Region): Region {
        val result = fields.filter { other.fields.contains(it) }
        return Region(result.toSet())
    }

    fun subtract(other: Region): Region {
        val result = fields.filterNot { other.fields.contains(it) }
        return Region(result.toSet())
    }

    fun isValid(): Boolean {
        val set = mutableSetOf<Int>()
        for (field in fields) {
            if (!field.isEmpty && !set.add(field.value)) {
                return false
            }
        }
        return true
    }

    fun fullFields(): List<Field> {
        return fields.filterNot { it.isEmpty }
    }

    fun emptyFields(): List<Field> {
        return fields.filter { it.isEmpty }
    }
}
