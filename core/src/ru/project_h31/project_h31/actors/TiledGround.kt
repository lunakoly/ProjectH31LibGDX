package ru.project_h31.project_h31.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Image
import java.util.*

class TiledGround : Group() {
    private val tiles = LinkedList<Image>()

    companion object {
        const val SCALE = 4f
    }

    init {
        val tileTexture = Texture("ground.png").apply {
            setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        }

        val count = Gdx.graphics.width / tileTexture.width + 2

        for (it in 0 until count) {
            val tile = Image(tileTexture)
            tile.setPosition(tile.width * it.toFloat() * SCALE, 0f)
            tile.setScale(SCALE)
            tiles.add(tile)
            addActor(tile)
        }
    }

    override fun act(delta: Float) {
        super.act(delta)

        val shift = stage.camera.position.x - tiles.first.x - Gdx.graphics.width.toFloat() / 2

        if (shift > tiles.first.width * SCALE) {
            val it = tiles.removeFirst()
            it.x = tiles.last.x + tiles.last.width * SCALE
            tiles.add(it)
        }
    }
}