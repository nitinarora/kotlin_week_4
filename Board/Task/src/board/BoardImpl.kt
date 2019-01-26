package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = object : SquareBoard {

    private var cellStore: MutableList<Cell> = mutableListOf()

    init {
        for (i in 1..width) {
            for (j in 1..width) {
                cellStore.add(Cell(i, j))
            }
        }
    }

    override val width: Int
        get() = width

    override fun getCellOrNull(i: Int, j: Int): Cell? =
            cellStore.firstOrNull { cell -> cell.i == i && cell.j == j }

    override fun getCell(i: Int, j: Int): Cell {
        val cellOrNull = getCellOrNull(i, j)
        return when (cellOrNull) {
            null -> throw IllegalArgumentException()
            else -> cellOrNull
        }
    }

    override fun getAllCells(): Collection<Cell> {
        return cellStore.toList().sortedWith(compareBy(Cell::i, Cell::j))
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return when {
            jRange.step < 0 -> {
                if (jRange.first > width) {
                    cellStore.toList().filter { it.i == i && it.j in jRange.last..width }.sortedByDescending { it.j }
                } else
                    cellStore.toList().filter { it.i == i && it.j in jRange.last..jRange.first }.sortedByDescending { it.j }
            }
            else -> {
                if (jRange.last > width) {
                    cellStore.toList().filter { it.i == i && it.j in jRange.first..width }
                } else
                    cellStore.toList().filter { it.i == i && it.j in jRange.first..jRange.last }
            }

        }
    }

override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
    return when {
        iRange.step < 0 -> {
            if (iRange.first > width) {
                cellStore.toList().filter { it.i in iRange.last..width && it.j == j  }.sortedByDescending { it.i }
            } else
                cellStore.toList().filter { it.i in iRange.last..iRange.first && it.j == j }.sortedByDescending { it.i }
        }
        else -> {
            if (iRange.last > width) {
                cellStore.toList().filter { it.i in iRange.first..width && it.j == j }
            } else
                cellStore.toList().filter { it.i in iRange.first..iRange.last  && it.j == j}
        }
    }
}

override fun Cell.getNeighbour(direction: Direction): Cell? {
    val i = this.i
    val j = this.j

    return when (direction) {
        UP -> getCellOrNull(i - 1, j)
        DOWN -> getCellOrNull(i + 1, j)
        LEFT -> getCellOrNull(i, j - 1)
        RIGHT -> getCellOrNull(i, j + 1)
    }
}

}

fun main() {
    val sb = createSquareBoard(2)
//    println(sb.getCell(1,2))
//    println(sb.getCellOrNull(2,2))
//    println(sb.getCellOrNull(0,2))
//    println(sb.getCellOrNull(3,2))
//    println(sb.getCell(3,2))
//    println(sb.getAllCells())
//    println(sb.getRow(1, 1..1))
//    println(sb.getRow(1, 1..2))
//    println(sb.getRow(1, 1..3))
//    println(sb.getRow(1, 2 downTo 1))
//    println(sb.getRow(1, 3 downTo 1))
//    println(sb.getRow(2, 1..1))
//    println(sb.getRow(2, 1..2))
//    println(sb.getRow(2, 2 downTo 1))
//    println(sb.getRow(2, 3 downTo 1))

    val gb = createGameBoard<Char>(2)
    println(gb.getAllCells())




}

fun <T> createGameBoard(width: Int): GameBoard<T> = object : GameBoard<T> {

    private var cellStore: List<Cell>
    private val sb: SquareBoard
    private var cellValueMap:MutableMap<Cell, T?> = mutableMapOf()

    init {
        sb = createSquareBoard(width)
        cellStore = sb.getAllCells() as List<Cell>

        for (cell in cellStore) {
            cellValueMap.put(cell, null)
        }
    }

    override fun get(cell: Cell): T? = cellValueMap.get(cell)

    override fun set(cell: Cell, value: T?) {
        cellValueMap.put(cell, value)
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> =
        cellValueMap.filter{ predicate(it.value) }.keys

    override fun find(predicate: (T?) -> Boolean): Cell? = this.filter(predicate).firstOrNull()

    override fun any(predicate: (T?) -> Boolean): Boolean {
       return cellValueMap.any{predicate(it.value)}
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cellValueMap.all { predicate(it.value) }
    }

    override val width: Int
        get() = sb.width

    override fun getCellOrNull(i: Int, j: Int): Cell? = sb.getCellOrNull(i,j)

    override fun getCell(i: Int, j: Int): Cell = sb.getCell(i,j)

    override fun getAllCells(): Collection<Cell>  = sb.getAllCells()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell>  = sb.getRow(i, jRange)

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> = sb.getColumn(iRange, j)

    override fun Cell.getNeighbour(direction: Direction): Cell? = sb.getCell(this.i, this.j).getNeighbour(direction)
}

