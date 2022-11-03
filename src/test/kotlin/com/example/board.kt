package com.example

typealias Board = List<Cell>

fun generateBoard(size: Int = 10): Board = (0 until size.times(size)).map {
    Cell(null)
}