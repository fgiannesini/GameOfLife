data class World(val width: Int, val height: Int, val cells: BooleanArray = BooleanArray(width * height)) {

    enum class Directions(val x: Int, val y: Int) {
        N(0, -1),
        NE(1, -1),
        E(1, 0),
        SE(1, 1),
        S(0, 1),
        SW(-1, 1),
        W(-1, 0),
        NW(-1, -1)
    }

    fun countNeighbors(x: Int, y: Int): Int {
        return Directions.values().map { Pair(x + it.x, y + it.y) }
            .filterNot { it.first >= width || it.first < 0 || it.second >= height || it.second < 0 }
            .count { cells[it.first + it.second * width] }
    }

    fun isAlive(x: Int, y: Int) = cells[x + y * width]

    fun build(transformation: (Int, Int) -> Boolean): World {
        val newCells = cells.indices.map {
            transformation.invoke(it % width, it / height)
        }.toBooleanArray()
        return World(width, height, newCells)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as World

        if (width != other.width) return false
        if (height != other.height) return false
        if (!cells.contentEquals(other.cells)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + cells.contentHashCode()
        return result
    }
}