package com.timgt.battleship

data class GameState(
    val turn: Turn = Turn.PlayerOne,
    val stage: Stage = Stage.Setup,
    val playerOne: Player,
    val playerTwo: Player,
    val playerOneBoard: Board = generateBoard(),
    val playerTwoBoard: Board = generateBoard(),
)