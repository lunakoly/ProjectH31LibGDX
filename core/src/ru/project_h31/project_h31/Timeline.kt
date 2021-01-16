package ru.project_h31.project_h31

class Timeline(var loop: Boolean = false) {
    private val actions = mutableListOf<Pair<Float, () -> Unit>>()

    private var age = 0f
    private var index = 0

    constructor(loop: Boolean = false, setup: Timeline.() -> Unit) : this(loop) {
        this.setup()
    }

    fun schedule(time: Float, code: () -> Unit) {
        actions.add(time to code)
    }

    fun update(delta: Float) {
        age += delta

        if (index < actions.size) {
            val next = actions[index]

            if (age >= next.first) {
                next.second()
                index += 1
            }
        } else if (loop) {
            index = 0
            age = 0f
        }
    }
}