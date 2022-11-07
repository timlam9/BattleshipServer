package com.timgt.battleship

fun Board.addShip(ship: Ship, anchor: Point, direction: Direction): Board {
    val anchorIndex = anchor.toIndex(10)
    val shipSize = ship.size

    val shipPoints = when (direction) {
        Direction.Horizontal -> (anchorIndex until anchorIndex + shipSize).toList()
        Direction.Vertical -> (0 until shipSize).map {
            anchorIndex + it * 10
        }
    }

    return mapIndexed { index, cell ->
        if (shipPoints.contains(index)) {
            Cell(ship)
        } else {
            cell
        }
    }
}

fun Board.fire(point: Point): Board {
    val pointIndex = point.toIndex(10)
    return mapIndexed { index, cell ->
        if (index == pointIndex) {
            cell.copy(isHit = true)
        } else cell
    }
}

fun Board.hasShips(): Boolean = any { it.ship != null }

fun Board.isWon(): Boolean = filter { cell ->
        cell.ship != null
    }.all { cell ->
        cell.isHit
    }


