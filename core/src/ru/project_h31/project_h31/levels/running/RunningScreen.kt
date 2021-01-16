package ru.project_h31.project_h31.levels.running

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import ru.project_h31.project_h31.GameMain

class RunningScreen(private val game: GameMain) : ScreenAdapter() {
    private var batch: SpriteBatch? = null

    private var runningStage: Stage? = null
    private var hudStage: Stage? = null

    private var backgroundStage: Stage? = null

    init {
        val backgroundActor = Image(Texture("background.png")).apply {
            setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            setPosition(0f, 170f)
        }

        backgroundStage = Stage()
        backgroundStage?.addActor(backgroundActor)

        val batch = SpriteBatch()
        val camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        val viewport = FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), camera)

        runningStage = RunningStage(viewport, batch, game)

        hudStage = HUDStage(game)

        this.batch = batch

//        game.beginMusic?.play()
        game.dropMusic?.play()
        game.theftMusic?.stop()
    }

    override fun show() {
        super.show()

        Gdx.input.inputProcessor = runningStage
//        Gdx.input.inputProcessor = object : InputAdapter() {
//
//        }
    }

    override fun hide() {
        super.hide()
        Gdx.input.inputProcessor = null
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

//        batch?.projectionMatrix = camera?.combined

        batch?.let {
            it.begin()
            backgroundStage?.act()
            backgroundStage?.draw()
            it.end()
        }

        runningStage?.act()
        runningStage?.draw()

        batch?.let {
            it.begin()
            hudStage?.act()
            hudStage?.draw()
            it.end()
        }
    }

    override fun dispose() {
        backgroundStage?.dispose()
        runningStage?.dispose()
        hudStage?.dispose()
    }
}