class GameOfLife {

    fun run(world: World): World {
        val newWorld = World(world.width, world.height)
        for (x in 0 until world.width) {
            for (y in 0 until world.height) {
                val shouldBeAlive = shouldBeAlive(
                    world.isAlive(x, y),
                    world.countNeighbors(x, y)
                )
                newWorld.withCell(x, y, shouldBeAlive)
            }
        }
        return newWorld
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
