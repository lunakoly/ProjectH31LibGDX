package ru.project_h31.project_h31.levels

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import ru.project_h31.project_h31.GameMain
import ru.project_h31.project_h31.actors.MenuSlides
import ru.project_h31.project_h31.levels.running.RunningScreen
import ru.project_h31.project_h31.loadPixelTexture

class MenuScreen(private val game: GameMain) : ScreenAdapter() {
    private var batch: SpriteBatch? = null

    private var hudStage: Stage? = null
//    private var background: Texture? = null

    private var backgroundStage: Stage? = null

    private var menuSlides: MenuSlides? = null

    init {
        batch = SpriteBatch()

//        background = Texture("main_menu.png")

        backgroundStage = Stage()

        menuSlides = MenuSlides()
        backgroundStage?.addActor(menuSlides)

        hudStage = Stage()
        Gdx.input.inputProcessor = hudStage

        val table = Table().apply {
            setFillParent(true)
//            debug = true

            val headerLabel = Label("[[Project H31]]", game.headerLabelStyle)
            headerLabel.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
//            headerLabel.color = Color.BLACK
            headerLabel.color = Color.WHITE
            headerLabel.setPosition(0f, 0f)
            headerLabel.setAlignment(Align.center)

            add(headerLabel).fillX().uniformX()
            row().pad(10f, 0f, 10f, 0f)

            val textButtonStyle = ImageButton.ImageButtonStyle().apply {
                up = TextureRegionDrawable(loadPixelTexture("ui/play_button_up.png"))
                down = TextureRegionDrawable(loadPixelTexture("ui/play_button_down.png"))
            }

            val startButton = ImageButton(textButtonStyle).apply {

            }

//            startButton.pad(0f, 50f, 0f, 50f)

            ClickListener.visualPressedDuration = 0f

            startButton.addListener(object : ClickListener() {
//                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
//                    game.reloadSound?.play()
//                    return true
//                }
//
//                override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
//                    game.reloadSound?.stop()
//                }

                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    game.screen = SlideshowScreen(game) {
                        RunningScreen(game)
                    }
//                    game.screen = RunningScreen(game)
                }
            })

            add(startButton).width(400f).height(400f * 19/50)//.fillX().uniformX()
        }

        hudStage?.addActor(table)

        game.mainMenuMusic?.play()
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch?.let {
            it.begin()
//            it.draw(background, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            backgroundStage?.act()
            backgroundStage?.draw()
            it.end()
        }

        batch?.let {
            it.begin()
            hudStage?.act()
            hudStage?.draw()
            it.end()
        }
    }

    override fun hide() {
        super.hide()
        Gdx.input.inputProcessor = null
    }
}