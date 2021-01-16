package ru.project_h31.project_h31.levels.running

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import ru.project_h31.project_h31.GameMain
import ru.project_h31.project_h31.actors.Obstacle
import ru.project_h31.project_h31.actors.Spawner
import ru.project_h31.project_h31.actors.TiledGround
import ru.project_h31.project_h31.levels.DeathScreen

class RunningStage(viewport: FitViewport, batch: Batch, private val game: GameMain) : Stage(viewport, batch) {
    companion object {
        const val CAMERA_SPEED = 700f
        const val GRAVITY = -6000f
    }

    private var groundActor: TiledGround? = null
    private var playerCharacter: PlayerCharacter? = null
    private var spawner: Spawner? = null

    private var verticalVelocity = 0f

    init {
        groundActor = TiledGround()
        addActor(groundActor)

        playerCharacter = PlayerCharacter().apply {
            setPosition(300f, 160f)
        }

        addActor(playerCharacter)

        spawner = Spawner().apply {
            setPosition(Gdx.graphics.width + 10f, 160f)
        }

        addActor(spawner)
    }

    private var jumpPressed = false
    private var secondJumpUsed = false
    private var touchUpAfterFirstJump = false

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        jumpPressed = true
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        jumpPressed = false

        if (!secondJumpUsed) {
            touchUpAfterFirstJump = true
        }

        return false
    }

    override fun act(delta: Float) {
        super.act(delta)

        camera?.translate(CAMERA_SPEED * delta, 0f, 0f)
        camera?.update()

        playerCharacter?.let {
            it.x += CAMERA_SPEED * delta

            if (it.y <= 160.5f && jumpPressed) {
                verticalVelocity = 2500f
                touchUpAfterFirstJump = false
            } else if (!secondJumpUsed && touchUpAfterFirstJump && jumpPressed) {
                verticalVelocity = 2500f/1.7f
                secondJumpUsed = true
                touchUpAfterFirstJump = false
            }

            verticalVelocity += GRAVITY * delta
            it.y += verticalVelocity * delta

            if (it.y < 160) {
                it.y = 160f
                verticalVelocity = 0f
                secondJumpUsed = false
            }

            for (that in actors) {
                if (that is Obstacle) {
                    val thatBounds = Rectangle(that.x, that.y, that.width * that.scale, that.height * that.scale)
                    val playerBounds = Rectangle(it.x, it.y, it.width, it.height)

                    if (playerBounds.overlaps(thatBounds)) {
                        game.screen = DeathScreen(game)
                    }
                }
            }
        }

        spawner?.let {
            it.x += CAMERA_SPEED * delta
        }
    }
}