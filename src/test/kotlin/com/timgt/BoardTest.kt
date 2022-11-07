package com.timgt

import org.junit.Assert.assertEquals
import org.junit.Test
import com.timgt.battleship.*

class BoardTest {

    @Test
    fun `generate board`() {
        val board = generateBoard()

        assertEquals(emptyBoard.size, board.size)
        assertEquals(emptyBoard, board)
    }

    @Test
    fun `add ship to board horizontally`() {
        val board = generateBoard()

        val boardWithShip = board.addShip(Ship.Destroyer, Point('A', 1), Direction.Horizontal)

        assertEquals(boardWithShipHorizontally, boardWithShip)
    }

    @Test
    fun `add ship to board vertically`() {
        val board = generateBoard()

        val boardWithShip = board.addShip(Ship.Destroyer, Point('A', 1), Direction.Vertical)

        assertEquals(boardWithShipVertically, boardWithShip)
    }

    @Test
    fun `add all ships to board`() {
        val board = generateBoard()
            .addShip(Ship.Destroyer, Point('A', 1), Direction.Vertical)
            .addShip(Ship.Submarine, Point('C', 10), Direction.Vertical)
            .addShip(Ship.Cruiser, Point('H', 3), Direction.Horizontal)
            .addShip(Ship.Battleship, Point('D', 5), Direction.Horizontal)
            .addShip(Ship.Carrier, Point('F', 2), Direction.Vertical)

        assertEquals(boardWithAllShips, board)
    }

    @Test
    fun `fire a shot and miss`() {
        val board = setupBoard()

        val missedPointBoard = board.fire(Point('A', 2))

        assertEquals(missedHitBoard, missedPointBoard)
    }

    @Test
    fun `fire a shot and hit`() {
        val board = setupBoard()

        val successHitPointBoard = board.fire(Point('A', 1))

        assertEquals(successHitBoard, successHitPointBoard)
    }
}
