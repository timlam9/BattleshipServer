package com.timgt

import com.timgt.battleship.Point
import com.timgt.battleship.toIndex
import org.junit.Assert
import org.junit.Test

class PointTest {

    @Test
    fun `convert anchor to index`() {
        val anchorIndexes: List<Int> =
            ('A'..'J').map { row ->
                (1..10).map { column ->
                    Point(row, column).toIndex(10)
                }
            }.flatten()
        val expectedIndexes = (0 until 100).toList()

        Assert.assertEquals(expectedIndexes, anchorIndexes)
    }
}