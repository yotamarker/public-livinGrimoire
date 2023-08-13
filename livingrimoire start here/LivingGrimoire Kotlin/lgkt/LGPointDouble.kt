package lgkt

import java.awt.Point

class LGPointDouble {
    var x: Double
    var y: Double

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    constructor() {
        x = 0.0
        y = 0.0
    }

    fun reset() {
        x = 0.0
        y = 0.0
    }

    fun shift(x: Double, y: Double) {
        this.x += x
        this.y += y
    }

    override fun toString(): String {
        return "$x,$y"
    }

    fun getDistance(p1: Point, p2: Point): Double {
        // returns the absolute distance between 2 points
        return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y)).toDouble())
    }
}