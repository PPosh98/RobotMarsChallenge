package com.example.robotmarschallenge

import org.junit.Assert.*
import org.junit.Test

class GridTest {

    @Test
    fun testCheckBounds() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Check if the grid can correctly identify valid coordinates within its bounds
        assertTrue(grid.checkBounds(0, 0))
        assertTrue(grid.checkBounds(5, 3))
        assertTrue(grid.checkBounds(2, 1))
        assertTrue(grid.checkBounds(4, 2))

        // Check if the grid can correctly identify invalid coordinates outside its bounds
        assertFalse(grid.checkBounds(-1, 0))
        assertFalse(grid.checkBounds(0, -1))
        assertFalse(grid.checkBounds(6, 3))
        assertFalse(grid.checkBounds(5, 4))
        assertFalse(grid.checkBounds(3, 5))
    }

    @Test
    fun testCheckScent() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Check if the grid can correctly identify that there is no scent at any coordinate initially
        assertFalse(grid.checkScent(0, 0))
        assertFalse(grid.checkScent(5, 3))
        assertFalse(grid.checkScent(2, 1))
        assertFalse(grid.checkScent(4, 2))

        // Mark some scents at some coordinates
        grid.markScent(0, 0)
        grid.markScent(5, 3)
        grid.markScent(2, 1)

        // Check if the grid can correctly identify that there is a scent at the marked coordinates
        assertTrue(grid.checkScent(0, 0))
        assertTrue(grid.checkScent(5, 3))
        assertTrue(grid.checkScent(2, 1))

        // Check if the grid can correctly identify that there is still no scent at the unmarked coordinates
        assertFalse(grid.checkScent(4, 2))
    }

    @Test
    fun testMarkScent() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Mark some scents at some coordinates
        grid.markScent(0, 0)
        grid.markScent(5, 3)
        grid.markScent(2, 1)

        // Check if the grid has stored the scents in its map correctly
        assertEquals(true, grid.scents[Pair(0, 0)])
        assertEquals(true, grid.scents[Pair(5, 3)])
        assertEquals(true, grid.scents[Pair(2, 1)])

        // Check if the grid has not stored any scents for the unmarked coordinates in its map
        assertNull(grid.scents[Pair(4, 2)])
    }
}