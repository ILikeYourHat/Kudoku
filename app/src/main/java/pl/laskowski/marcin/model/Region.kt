package pl.laskowski.marcin.model

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
        val set: MutableSet<Int> = HashSet()
        for (field in fields) {
            if (alreadyContainsFieldValue(field, set)) {
                return false
            } else {
                set.add(field.value())
            }
        }
        return true
    }

    fun containsFieldWithSamePosition(field: Field): Boolean {
        return fields.any { it.haveSamePosition(field) }
    }

    fun fullFields(): List<Field> {
        return fields.filterNot { it.isEmpty }
    }

    fun emptyFields(): List<Field> {
        return fields.filter { it.isEmpty }
    }

    private fun alreadyContainsFieldValue(field: Field, numbers: Set<Int>): Boolean {
        return !field.isEmpty && numbers.contains(field.value())
    }
}