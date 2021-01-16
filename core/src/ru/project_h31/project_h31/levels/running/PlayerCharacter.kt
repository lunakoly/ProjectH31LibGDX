package ru.project_h31.project_h31.levels.running

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import ru.project_h31.project_h31.generateFrameAnimation

class PlayerCharacter : Actor() {
    companion object {
        const val SCALE = 4f
    }

    private var running: Animation<TextureAtlas.AtlasRegion>? = null

    private var age = 0f

    init {
        running = generateFrameAnimation("player_running.atlas", 0.1f)

        running?.getKeyFrame(0f)?.let {
            width = it.regionWidth * SCALE
            height = it.regionHeight * SCALE
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        age += Gdx.graphics.deltaTime
        val frame = running?.getKeyFrame(age)

        stage.batch.draw(frame, x, y, width, height)
    }
}