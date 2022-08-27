class GameOfLife {

    fun run(world: World): World {
        return world.build { x, y ->
            shouldBeAlive(
                world.isAlive(x, y),
                world.countNeighbors(x, y)
            )
        }
    }

    private fun shouldBeAlive(isAlive: Boolean, neighborsCount: Int): Boolean {
        return when {
            neighborsCount < 2 && isAlive -> false
            (neighborsCount == 2 || neighborsCount == 3) && isAlive -> true
            neighborsCount == 3 -> true
            neighborsCount > 3 && isAlive -> false
            else -> false
        }
    }

}
