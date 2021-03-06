package board

import org.junit.Assert
import org.junit.Test

class TestSquareBoard {

    @Test
    fun testAllCells() {
        val board = createSquareBoard(2)
        val cells = board.getAllCells().sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })
        Assert.assertEquals("[(1, 1), (1, 2), (2, 1), (2, 2)]", cells.toString())
    }

    @Test
    fun testCell() {
        val board = createSquareBoard(2)
        val cell = board.getCellOrNull(1, 2)
        Assert.assertEquals(1, cell?.i)
        Assert.assertEquals(2, cell?.j)
    }

    @Test
    fun testNoCell() {
        val board = createSquareBoard(2)
        val cell = board.getCellOrNull(3, 3)
        Assert.assertEquals(null, cell)
    }

    @Test
    fun testRow() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 1..2)
        Assert.assertEquals("[(1, 1), (1, 2)]", row.toString())
    }

    @Test
    fun testRowReversed() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 2 downTo 1)
        Assert.assertEquals("[(1, 2), (1, 1)]", row.toString())
    }

    @Test
    fun testRowReversed2() {
        val board = createSquareBoard(2)
        val row = board.getRow(2, 2 downTo 1)
        Assert.assertEquals("[(2, 2), (2, 1)]", row.toString())
    }

    @Test
    fun testRowWrongRange() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 1..10)
        Assert.assertEquals("[(1, 1), (1, 2)]", row.toString())
    }

    @Test
    fun testRowWrongRange2() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 10..10)
        Assert.assertEquals("[]", row.toString())
    }

    @Test
    fun testColumn() {
        val board = createSquareBoard(2)
        val row = board.getColumn(1..2, 1)
        Assert.assertEquals("[(1, 1), (2, 1)]", row.toString())
    }

    @Test
    fun testColumnReversed() {
        val board = createSquareBoard(2)
        val row = board.getColumn(2 downTo 1, 1)
        Assert.assertEquals("[(2, 1), (1, 1)]", row.toString())
    }

    @Test
    fun testColumnWrongRange() {
        val board = createSquareBoard(2)
        val row = board.getColumn(1..10, 1)
        Assert.assertEquals("[(1, 1), (2, 1)]", row.toString())
    }

    @Test
    fun testColumnWrongRangeReversed() {
        val board = createSquareBoard(2)
        val row = board.getColumn(10 downTo 1, 1)
        Assert.assertEquals("[(2, 1), (1, 1)]", row.toString())
    }

    @Test
    fun testColumnWrongRangeReversed3() {
        val board = createSquareBoard(2)
        val row = board.getColumn(10 downTo 1, 2)
        Assert.assertEquals("[(2, 2), (1, 2)]", row.toString())
    }

    @Test
    fun testColumnWrongRangeReversed2() {
        val board = createSquareBoard(2)
        val row = board.getColumn(10..10, 1)
        Assert.assertEquals("[]", row.toString())
    }

    @Test
    fun testNeighbour() {
        val board = createSquareBoard(2)
        with(board) {
            val cell = getCellOrNull(1, 1)
            Assert.assertNotNull(cell)
            Assert.assertEquals(null, cell!!.getNeighbour(Direction.UP))
            Assert.assertEquals(null, cell.getNeighbour(Direction.LEFT))
            Assert.assertEquals("(2, 1)", cell.getNeighbour(Direction.DOWN).toString())
            Assert.assertEquals("(1, 2)", cell.getNeighbour(Direction.RIGHT).toString())
        }
    }
}
