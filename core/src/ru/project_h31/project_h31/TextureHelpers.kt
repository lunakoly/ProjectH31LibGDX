package ru.project_h31.project_h31

import com.badlogic.gdx.graphics.Texture

fun loadPixelTexture(internalPath: String): Texture {
    return Texture(internalPath).apply {
        setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
    }
}
