package com.example.robotmarschallenge

class Grid(
    val width: Int,
    val height: Int,
    val scents: MutableMap<Pair<Int, Int>, Boolean> = mutableMapOf()
) {

    fun checkBounds(x: Int, y: Int): Boolean {
        return x in 0..width && y in 0..height
    }

    fun checkScent(x: Int, y: Int): Boolean {
        return scents.getOrDefault(Pair(x, y), false)
    }

    fun markScent(x: Int, y: Int) {
        scents[Pair(x, y)] = true
    }
}