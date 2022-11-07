package com.timgt.battleship

data class Cell(
    val ship: Ship? = null,
    val isHit: Boolean = false
)