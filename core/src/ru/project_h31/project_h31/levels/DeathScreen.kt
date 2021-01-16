package ru.project_h31.project_h31.levels

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import ru.project_h31.project_h31.GameMain
import ru.project_h31.project_h31.Timeline
import ru.project_h31.project_h31.levels.running.RunningScreen

class DeathScreen(private val game: GameMain) : ScreenAdapter() {
    companion object {
        const val DELAY = 7f
    }

    private var batch: SpriteBatch? = null
    private var background: Texture? = null

    private var stage: Stage? = null

    private var timeout = 0f

    private val timeline = Timeline {
        schedule(2.519f) {
            background = Texture("death_screen.png")
        }
    }

    init {
        batch = SpriteBatch()
        stage = Stage()

        background = Texture("death_screen_early.png")
        game.deathSound?.play()
        game.beginMusic?.stop()
        game.dropMusic?.stop()

        val headerLabel = Label("Good evening.", game.headerLabelStyle)
        headerLabel.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        headerLabel.setPosition(0f, 0f)
        headerLabel.setAlignment(Align.center)
        stage?.addActor(headerLabel)
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        timeout += delta

        timeline.update(delta)

        batch?.let {
            it.begin()
            it.draw(background, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            stage?.act()
            stage?.draw()
            it.end()
        }

        if (timeout > DELAY) {
            game.screen = RunningScreen(game)
        }
    }

    override fun show() {
        super.show()
        Gdx.input.inputProcessor = null
    }

    override fun hide() {
        super.hide()
        Gdx.input.inputProcessor = null
    }
}