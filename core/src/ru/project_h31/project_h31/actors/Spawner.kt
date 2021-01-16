package ru.project_h31.project_h31.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import java.util.*
import kotlin.random.Random

class Spawner(private val interval: Float = 1.4f, private val intervalDelta: Float = 0.4f) : Group() {
    data class SpawnableObstacle(
            val path: String,
            val scale: Float,
            val shiftUp: Float
    )

    companion object {
        private val obstacleTexturePaths = listOf(
                SpawnableObstacle("box.png", 3f, 0f),
                SpawnableObstacle("wheel.png", 4f, 0f),
                SpawnableObstacle("bullet.png",2f, 90f),
                SpawnableObstacle("tank.png", 8f, 0f)
        )
    }

    private var waitTime = 0f
    private var intervalEnd = calculateIntervalEnd()

    private val obstacles = LinkedList<Obstacle>()

    private fun calculateIntervalEnd() = interval + (Random.nextFloat() * 2 - 1) * intervalDelta

    override fun act(delta: Float) {
        super.act(delta)

        if (obstacles.isNotEmpty()) {
            val first = obstacles.first

            val shift = stage.camera.position.x - first.x - Gdx.graphics.width.toFloat() / 2

            if (shift > first.width * first.scale) {
                obstacles.removeFirst().remove()
            }
        }

        waitTime += delta

        if (waitTime > intervalEnd) {
            emit(delta)

            intervalEnd = calculateIntervalEnd()
            waitTime = 0f
        }
    }

    private fun emit(delta: Float) {
        val (obstacleTexturePath, scale, shiftUp) = obstacleTexturePaths.random()
        val obstacle = Obstacle(Texture(obstacleTexturePath), scale)
        obstacle.setPosition(x, y + shiftUp)

        obstacles.add(obstacle)
        stage.addActor(obstacle)
    }
}