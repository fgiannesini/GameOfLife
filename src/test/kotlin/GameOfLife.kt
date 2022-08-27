class GameOfLife {

    fun run(cells: BooleanArray): BooleanArray {
        return if (cells.count { it } > 2) cells else BooleanArray(9)
    }

}
