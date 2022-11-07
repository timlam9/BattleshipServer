package com.timgt.battleship

class GameEngine {
    private lateinit var gameState: GameState

    fun start(playerOne: Player, playerTwo: Player): GameState {
        gameState = GameState(playerOne= playerOne, playerTwo = playerTwo)
        return gameState
    }

    fun addShips(player: Player, shipsSetup: List<ShipPlacement>): GameState {
        val isPlayerOne = player.id == gameState.playerOne.id
        var board = if (isPlayerOne) gameState.playerOneBoard else gameState.playerTwoBoard

        shipsSetup.forEach {
            board = board.addShip(it.ship, it.point, it.direction)
        }

        gameState = if (isPlayerOne)
            gameState.copy(playerOneBoard = board)
        else
            gameState.copy(playerTwoBoard = board)

        if (gameState.playerOneBoard.hasShips() && gameState.playerTwoBoard.hasShips()) {
            gameState = gameState.copy(stage = Stage.Playing)
        }

        return gameState
    }
}