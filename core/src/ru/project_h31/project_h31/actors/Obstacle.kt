package ru.project_h31.project_h31.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Obstacle(texture: Texture, val scale: Float = 1f) : Image(texture) {
    init {
        setScale(scale)
    }
}