import kotlin.math.sqrt

class GameOfLife {

    fun run(cells: BooleanArray): BooleanArray {
        val size = sqrt(cells.size.toDouble()).toInt()
        val world = World(size, size, cells)
        return run(world).cells
    }

    fun run(world: World): World {
        val newWorld = World(world.width, world.height)
        for (x in 0 until world.width) {
            for (y in 0 until world.height) {
                val shouldBeAlive = shouldBeAlive(
                    world.isAlive(x, y),
                    world.countNeighbours(x, y)
                )
                newWorld.withCell(x, y, shouldBeAlive)
            }
        }
        return newWorld
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

}
