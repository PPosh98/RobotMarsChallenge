package com.example.robotmarschallenge

class Robot(
    var x: Int,
    var y: Int,
    var orientation: Char,
    var instructions: String,
    var status: String = ""
) {

    fun executeInstruction(instruction: Char, grid: Grid) {
        when (instruction) {
            'L' -> turnLeft()
            'R' -> turnRight()
            'F' -> moveForward(grid)
        }
    }

    fun turnLeft() {
        orientation = when (orientation) {
            'N' -> 'W'
            'W' -> 'S'
            'S' -> 'E'
            'E' -> 'N'
            else -> orientation
        }
    }

    fun turnRight() {
        orientation = when (orientation) {
            'N' -> 'E'
            'E' -> 'S'
            'S' -> 'W'
            'W' -> 'N'
            else -> orientation
        }
    }

    fun moveForward(grid: Grid) {
        val prevX = x
        val prevY = y

        when (orientation) {
            'N' -> y++
            'E' -> x++
            'S' -> y--
            'W' -> x--
        }

        if (!grid.checkBounds(x, y)) {
            if (!grid.checkScent(prevX, prevY)) {
                status = "LOST"
                grid.markScent(prevX, prevY)
            }

            x = prevX
            y = prevY
        }
    }
}