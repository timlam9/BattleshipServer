package com.timgt

import com.timgt.battleship.*
import org.junit.Test
import kotlin.test.assertEquals

class PlayerTest {

    @Test
    fun `start game`() {
        val game = GameEngine()

        val gameState = game.start(playerOne = Player("paixtaras"), playerTwo = Player("ampalos"))

        assertEquals(GameState(playerOne = Player("paixtaras"), playerTwo = Player("ampalos")), gameState)
    }

    @Test
    fun `setup stage`() {
        val game = GameEngine()
        val playerOne = Player("paixtaras")
        val shipsSetupOne = listOf(
            ShipPlacement(Ship.Destroyer, Point('A', 1), Direction.Vertical),
            ShipPlacement(Ship.Submarine, Point('C', 10), Direction.Vertical),
            ShipPlacement(Ship.Cruiser, Point('H', 3), Direction.Horizontal),
            ShipPlacement(Ship.Battleship, Point('D', 5), Direction.Horizontal),
            ShipPlacement(Ship.Carrier, Point('F', 2), Direction.Vertical),
        )
        val playerTwo = Player("ampalos")
        val shipsSetupTwo = listOf(
            ShipPlacement(Ship.Destroyer, Point('A', 1), Direction.Vertical),
            ShipPlacement(Ship.Submarine, Point('C', 10), Direction.Vertical),
            ShipPlacement(Ship.Cruiser, Point('H', 3), Direction.Horizontal),
            ShipPlacement(Ship.Battleship, Point('D', 5), Direction.Horizontal),
            ShipPlacement(Ship.Carrier, Point('F', 2), Direction.Vertical),
        )
        game.start(playerOne = playerOne, playerTwo = playerTwo)

        game.addShips(playerOne, shipsSetupOne)
        val gameState = game.addShips(playerTwo, shipsSetupTwo)

        assertEquals(
            GameState(
                turn = Turn.PlayerOne,
                stage = Stage.Playing,
                playerOne = playerOne,
                playerTwo = playerTwo,
                playerOneBoard = boardWithAllShips,
                playerTwoBoard = boardWithAllShips,
            ),
            gameState
        )
    }
}