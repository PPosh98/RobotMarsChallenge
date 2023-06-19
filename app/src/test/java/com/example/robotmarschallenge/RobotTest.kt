package com.example.robotmarschallenge

import org.junit.Assert.*
import org.junit.Test

class RobotTest {

    @Test
    fun testTurnLeft() {
        // Create a robot with initial position (1, 1) and orientation N
        val robot = Robot(1, 1, 'N', "")

        robot.turnLeft()
        assertEquals('W', robot.orientation)

        robot.turnLeft()
        assertEquals('S', robot.orientation)

        robot.turnLeft()
        assertEquals('E', robot.orientation)

        robot.turnLeft()
        assertEquals('N', robot.orientation)
    }

    @Test
    fun testTurnRight() {
        // Create a robot with initial position (1, 1) and orientation N
        val robot = Robot(1, 1, 'N', "")

        robot.turnRight()
        assertEquals('E', robot.orientation)

        robot.turnRight()
        assertEquals('S', robot.orientation)

        robot.turnRight()
        assertEquals('W', robot.orientation)

        robot.turnRight()
        assertEquals('N', robot.orientation)
    }

    @Test
    fun testMoveForwardWithinBounds() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Create a robot with initial position (1, 1) and orientation N
        val robot = Robot(1, 1, 'N', "")

        // Move the robot forward and assert that its position is (1, 2) and its status is empty
        robot.moveForward(grid)
        assertEquals(1, robot.x)
        assertEquals(2, robot.y)
        assertEquals("", robot.status)

        robot.orientation = 'E'

        // Move the robot forward and assert that its position is (2, 2) and its status is empty
        robot.moveForward(grid)
        assertEquals(2, robot.x)
        assertEquals(2, robot.y)
        assertEquals("", robot.status)

        robot.orientation = 'S'

        // Move the robot forward and assert that its position is (2, 1) and its status is empty
        robot.moveForward(grid)
        assertEquals(2, robot.x)
        assertEquals(1, robot.y)
        assertEquals("", robot.status)

        robot.orientation = 'W'

        // Move the robot forward and assert that its position is (1, 1) and its status is empty
        robot.moveForward(grid)
        assertEquals(1, robot.x)
        assertEquals(1, robot.y)
        assertEquals("", robot.status)
    }

    @Test
    fun testMoveForwardOffEdgeAndLost() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Create a robot with initial position (3, 2) and orientation N
        val robot = Robot(3, 2, 'N', "")

        // Move the robot forward and assert that its position is (3, 3) and its status is empty
        robot.moveForward(grid)
        assertEquals(3, robot.x)
        assertEquals(3, robot.y)
        assertEquals("", robot.status)

        // Move the robot forward again and assert that its position is (3, 3) and its status is LOST
        robot.moveForward(grid)
        assertEquals(3, robot.x)
        assertEquals(3, robot.y)
        assertEquals("LOST", robot.status)

        // Check if there is a scent left by the lost robot at (3, 3)
        assertTrue(grid.checkScent(3, 3))
    }

    @Test
    fun testMoveForwardOffEdgeAndIgnore() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Create a robot with initial position (0, 3) and orientation W
        val robot = Robot(0, 3, 'W', "")

        // Move the robot forward and assert that its position is (0, 3) and its status is LOST
        robot.moveForward(grid)
        assertEquals(0, robot.x)
        assertEquals(3, robot.y)
        assertEquals("LOST", robot.status)

        // Check if there is a scent left by the lost robot at (0, 3)
        assertTrue(grid.checkScent(0, 3))

        // Create another robot with initial position (2, 3) and orientation W
        val robot2 = Robot(2, 3, 'W', "")

        // Move the second robot forward twice and assert that its position is (0, 3) and its status is empty
        robot2.moveForward(grid)
        robot2.moveForward(grid)
        assertEquals(0, robot2.x)
        assertEquals(3, robot2.y)
        assertEquals("", robot2.status)

        // Move the second robot forward again and assert that its position is still (0, 3) and its status is still empty
        robot2.moveForward(grid)
        assertEquals(0, robot2.x)
        assertEquals(3, robot2.y)
        assertEquals("", robot2.status)
    }

    @Test
    fun testExecuteInstruction() {
        // Create a grid with width 5 and height 3
        val grid = Grid(5, 3)

        // Create a robot with initial position (1, 1) and orientation E and instructions RFRFRFRF
        val robot = Robot(1, 1, 'E', "RFRFRFRF")

        // Execute the first instruction and assert that the robot's state is (1, 1, S)
        robot.executeInstruction(robot.instructions[0], grid)
        assertEquals(1, robot.x)
        assertEquals(1, robot.y)
        assertEquals('S', robot.orientation)

        // Execute the second instruction and assert that the robot's state is (1, 0, S)
        robot.executeInstruction(robot.instructions[1], grid)
        assertEquals(1, robot.x)
        assertEquals(0, robot.y)
        assertEquals('S', robot.orientation)

        // Execute the third instruction and assert that the robot's state is (1, 0, W)
        robot.executeInstruction(robot.instructions[2], grid)
        assertEquals(1, robot.x)
        assertEquals(0, robot.y)
        assertEquals('W', robot.orientation)

        // Execute the fourth instruction and assert that the robot's state is (0, 0, W)
        robot.executeInstruction(robot.instructions[3], grid)
        assertEquals(0, robot.x)
        assertEquals(0, robot.y)
        assertEquals('W', robot.orientation)

        // Execute the fifth instruction and assert that the robot's state is (0, 0, N)
        robot.executeInstruction(robot.instructions[4], grid)
        assertEquals(0, robot.x)
        assertEquals(0, robot.y)
        assertEquals('N', robot.orientation)

        // Execute the sixth instruction and assert that the robot's state is (0, 1, N)
        robot.executeInstruction(robot.instructions[5], grid)
        assertEquals(0, robot.x)
        assertEquals(1, robot.y)
        assertEquals('N', robot.orientation)

        // Execute the seventh instruction and assert that the robot's state is (0, 1, E)
        robot.executeInstruction(robot.instructions[6], grid)
        assertEquals(0, robot.x)
        assertEquals(1, robot.y)
        assertEquals('E', robot.orientation)

        // Execute the eighth instruction and assert that the robot's state is (1, 1, E)
        robot.executeInstruction(robot.instructions[7], grid)
        assertEquals(1, robot.x)
        assertEquals(1, robot.y)
        assertEquals('E', robot.orientation)
    }
}