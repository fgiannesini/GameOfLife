import kotlin.math.sqrt

class GameOfLife {

    fun run(cells: BooleanArray): BooleanArray {
        val size = sqrt(cells.size.toDouble()).toInt()
        val world = World(size, size, cells)
        val newWorld = World(size, size, BooleanArray(cells.size))
        for (x in 0 until world.width) {
            for (y in 0 until world.height) {
                val shouldBeAlive = shouldBeAlive(
                    world.isAlive(x, y),
                    world.countNeighbours(x, y)
                )
                newWorld.withCell(x, y, shouldBeAlive)
            }
        }
        return newWorld.cells
    }

    private fun shouldBeAlive(isAlive: Boolean, neighbourgsCount: Int): Boolean {
        return when {
            neighbourgsCount < 2 && isAlive -> false
            (neighbourgsCount == 2 || neighbourgsCount == 3) && isAlive -> true
            neighbourgsCount == 3 && !isAlive -> true
            neighbourgsCount > 3 && isAlive -> false
            else -> isAlive
        }
    }

    data class World(val width: Int, val height: Int, val cells: BooleanArray) {
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

        fun countNeighbours(x: Int, y: Int): Int {
            return Directions.values().map { Pair(x + it.x, y + it.y) }
                .filterNot { it.first >= width || it.first < 0 || it.second >= height || it.second < 0 }
                .count { cells[it.first + it.second * width] }
        }

        fun isAlive(x: Int, y: Int) = cells[x + y * width]

        fun withCell(x: Int, y: Int, value: Boolean) {
            cells[ x + y * width] = value
        }
    }

}
