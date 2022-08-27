import kotlin.math.sqrt

class GameOfLife {

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

    fun run(cells: BooleanArray): BooleanArray {
        return cells.mapIndexed { index: Int, _: Boolean -> isAlive(index, cells) }.toBooleanArray()
    }

    private fun isAlive(index: Int, cells: BooleanArray): Boolean {
        val size = sqrt(cells.size.toDouble()).toInt()
        val x = index % size
        val y = index / size
        val count = Directions.values().map { Pair(x + it.x, y + it.y) }
            .filterNot { it.first >= size || it.first < 0 || it.second >= size || it.second < 0 }
            .count { cells[it.first + it.second * size] }
        return when {
            count < 2 && cells[index] -> false
            (count == 2 || count == 3) && cells[index] -> true
            count == 3 && !cells[index] -> true
            count > 3 && cells[index] -> false
            else -> cells[index]
        }
    }

}
