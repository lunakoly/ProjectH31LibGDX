package ru.project_h31.project_h31

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

const val ROBOTO_REGULAR_FONT_PATH = "fonts/Roboto-Regular.ttf"
const val ROBOTO_BOLD_FONT_PATH = "fonts/Roboto-Bold.ttf"
const val PIXEL_FONT_PATH = "fonts/Pixel.ttf"

fun withFontGenerator(path: String, code: FreeTypeFontGenerator.() -> Unit) {
    val generator = FreeTypeFontGenerator(Gdx.files.internal(path))
    generator.code()
    generator.dispose()
}

fun FreeTypeFontGenerator.generateVariant(build: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit): BitmapFont {
    return generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply(build))
}
