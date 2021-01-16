package ru.project_h31.project_h31.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Image
import java.util.*

class MenuSlides : Group() {
    companion object {
        const val SPEED = 50f
    }

    private val tiles = LinkedList<Image>()

    init {
        val slideTexture = Texture("main_menu.png").apply {
            setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        }

        val count = Gdx.graphics.width * 3

        for (it in 0 until count) {
            val slide = Image(slideTexture)
            slide.width = Gdx.graphics.width.toFloat()
            slide.height = Gdx.graphics.height.toFloat()
            slide.setPosition(slide.width * it.toFloat(), 0f)
            tiles.add(slide)
            addActor(slide)
        }
    }

    override fun act(delta: Float) {
        super.act(delta)

        for (it in children) {
            it.x -= SPEED * delta
        }

        if (tiles.first.x + Gdx.graphics.width.toFloat() < 0) {
            val it = tiles.removeFirst()
            it.x = tiles.last.x + tiles.last.width
            tiles.add(it)
        }
    }
}