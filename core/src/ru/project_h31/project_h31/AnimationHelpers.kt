package ru.project_h31.project_h31

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas

fun generateFrameAnimation(atlasPath: String, frameDuration: Float = 0.5f, region: String = "running"): Animation<TextureAtlas.AtlasRegion> {
    val atlas = TextureAtlas(Gdx.files.internal(atlasPath))
    val frames = atlas.findRegions(region)
    return Animation(frameDuration, frames, Animation.PlayMode.LOOP)
}
