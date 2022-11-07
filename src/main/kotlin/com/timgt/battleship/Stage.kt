package com.timgt.battleship

sealed class Stage {
    object Setup: Stage()
    data class Playing(val player: Player): Stage()
    data class GameOver(val player: Player): Stage()
}