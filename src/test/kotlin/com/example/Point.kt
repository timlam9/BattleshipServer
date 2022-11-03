package com.example

data class Point(
    val row: Char,
    val column: Int
)

fun Point.toIndex(size: Int): Int = (((row.code - 65) * size) + column) - 1