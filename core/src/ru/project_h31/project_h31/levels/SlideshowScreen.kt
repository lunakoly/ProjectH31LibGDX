package ru.project_h31.project_h31.levels

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.project_h31.project_h31.GameMain
import ru.project_h31.project_h31.Timeline

class SlideshowScreen(
        private val game: GameMain,
        private val getNextScreen: () -> Screen,
) : ScreenAdapter() {
    companion object {
        const val DELAY = 3f
    }

    private var batch: SpriteBatch? = null
    private var texture: Texture? = null

    private val timeline = Timeline {
        schedule(DELAY) {
            showSlide("slides/weapon_room.png")
        }
        schedule(DELAY * 2) {
            showSlide("slides/theft.png")
        }
        schedule(DELAY * 3) {
            showSlide("slides/pursuit.png")
        }
        schedule(DELAY * 4) {
            game.screen = getNextScreen()
        }
    }

    init {
        batch = SpriteBatch()
        showSlide("slides/army_building.png")

        game.beginMusic?.position

        game.theftMusic?.play()
        game.mainMenuMusic?.stop()
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // IT DOESNT WORK THIS FUCK WONT WORK
        // FUNCKING BLENDING WONT WORK
        // FUCK IT

        timeline.update(delta)

        batch?.let {
            it.begin()

            texture?.let { that ->
                it.draw(that, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            }

            it.end()
        }
    }

    private fun showSlide(path: String) {
        texture = Texture(path)
    }
}