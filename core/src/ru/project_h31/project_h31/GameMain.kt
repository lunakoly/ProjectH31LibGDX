package ru.project_h31.project_h31

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label
import ru.project_h31.project_h31.levels.MenuScreen

class GameMain : Game() {
    var headerLabelStyle: Label.LabelStyle? = null

    var deathSound: Sound? = null
    var reloadSound: Sound? = null
    var shotSound: Sound? = null

    var mainMenuMusic: Music? = null
    var beginMusic: Music? = null
    var dropMusic: Music? = null
    var theftMusic: Music? = null

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        FreeTypeFontGenerator.setMaxTextureSize(2048)

        headerLabelStyle = Label.LabelStyle().apply {
            withFontGenerator(PIXEL_FONT_PATH) {
                font = generateVariant {
                    size = 400
                    borderWidth = 10f
//                    borderColor = Color.WHITE
//                    color = Color.BLACK
                    minFilter = Texture.TextureFilter.Nearest
                    magFilter = Texture.TextureFilter.Nearest
//                    shadowOffsetX = 3
//                    shadowOffsetY = 3
//                    shadowColor = Color.WHITE
                }
            }
        }

//        headerLabelStyle?.font?.data?.scale(1.5f)

        deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/wasted.mp3"))
        reloadSound = Gdx.audio.newSound(Gdx.files.internal("sounds/reload.mp3"))
        shotSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shot.mp3"))

//        val assetManager = AssetManager()
//        assetManager.load("sounds/reload.mp3", Sound::class.java)
//        assetManager.finishLoading()
//        reloadSound = assetManager.get("sounds/reload.mp3", Sound::class.java)

        mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/main_menu.wav")).apply {
            isLooping = true
        }

        beginMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/begin.wav"))

        dropMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/drop.wav")).apply {
            isLooping = true
        }

        beginMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/begin.wav"))

        theftMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/theft_music.mp3"))

        screen = MenuScreen(this)
    }

    override fun dispose() {
        headerLabelStyle?.font?.dispose()
    }
}