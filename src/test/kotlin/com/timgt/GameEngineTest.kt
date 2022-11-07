package com.timgt

import com.timgt.battleship.*
import org.junit.Test
import kotlin.test.assertEquals

class GameEngineTest {

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
                stage = Stage.Playing(playerOne),
                playerOne = playerOne,
                playerTwo = playerTwo,
                playerOneBoard = boardWithAllShips,
                playerTwoBoard = boardWithAllShips,
            ),
            gameState
        )
    }

    @Test
    fun `player one turn`() {
        val game = GameEngine()
        val gameState = givenStagePlaying(game)

        val updateGameState = game.fire(Point('A', 1))

        assertEquals(
            gameState.copy(
                stage = Stage.Playing(gameState.playerTwo),
                playerTwoBoard = successHitBoard,
            ),
            updateGameState
        )
    }

    @Test
    fun `sink a ship`() {
        val game = GameEngine()
        val gameState = givenStagePlaying(game)

        game.fire(Point('A', 1))
        game.fire(Point('A', 1))
        val updateGameState = game.fire(Point('B', 1))

        assertEquals(
            gameState.copy(
                stage = Stage.Playing(gameState.playerTwo),
                playerOneBoard = successHitBoard,
                playerTwoBoard = successSunkenShipBoard,
            ),
            updateGameState
        )
    }
    @Test
    fun `game over`() {
        val game = GameEngine()
        val gameState = givenStagePlaying(game)

        game.fire(Point('A', 1))
        game.fire(Point('A', 1))
        game.fire(Point('B', 1))
        game.fire(Point('B', 1))
        game.fire(Point('C', 10))
        game.fire(Point('C', 10))
        game.fire(Point('D', 5))
        game.fire(Point('D', 5))
        game.fire(Point('D', 6))
        game.fire(Point('D', 6))
        game.fire(Point('D', 7))
        game.fire(Point('D', 7))
        game.fire(Point('D', 8))
        game.fire(Point('D', 8))
        game.fire(Point('D', 10))
        game.fire(Point('D', 10))
        game.fire(Point('E', 10))
        game.fire(Point('E', 10))
        game.fire(Point('H', 3))
        game.fire(Point('H', 3))
        game.fire(Point('H', 4))
        game.fire(Point('H', 4))
        game.fire(Point('H', 5))
        game.fire(Point('H', 5))
        game.fire(Point('F', 2))
        game.fire(Point('F', 2))
        game.fire(Point('G', 2))
        game.fire(Point('G', 2))
        game.fire(Point('H', 2))
        game.fire(Point('H', 2))
        game.fire(Point('I', 2))
        game.fire(Point('I', 2))
        val updateGameState = game.fire(Point('J', 2))

        assertEquals(
            gameState.copy(
                stage = Stage.GameOver(gameState.playerOne),
                playerOneBoard = playerTwoLostBoard,
                playerTwoBoard = playerOneWonBoard,
            ),
            updateGameState
        )
    }

    private fun givenStagePlaying(game: GameEngine): GameState {
        val playerOne = Player("paixtaras")
        val playerTwo = Player("ampalos")
        val shipsSetupOne = listOf(
            ShipPlacement(Ship.Destroyer, Point('A', 1), Direction.Vertical),
            ShipPlacement(Ship.Submarine, Point('C', 10), Direction.Vertical),
            ShipPlacement(Ship.Cruiser, Point('H', 3), Direction.Horizontal),
            ShipPlacement(Ship.Battleship, Point('D', 5), Direction.Horizontal),
            ShipPlacement(Ship.Carrier, Point('F', 2), Direction.Vertical),
        )
        val shipsSetupTwo = listOf(
            ShipPlacement(Ship.Destroyer, Point('A', 1), Direction.Vertical),
            ShipPlacement(Ship.Submarine, Point('C', 10), Direction.Vertical),
            ShipPlacement(Ship.Cruiser, Point('H', 3), Direction.Horizontal),
            ShipPlacement(Ship.Battleship, Point('D', 5), Direction.Horizontal),
            ShipPlacement(Ship.Carrier, Point('F', 2), Direction.Vertical),
        )
        game.start(playerOne = playerOne, playerTwo = playerTwo)
        game.addShips(playerOne, shipsSetupOne)
        return game.addShips(playerTwo, shipsSetupTwo)
    }
}