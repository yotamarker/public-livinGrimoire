package lgkt

import java.awt.Point

class LGPointInt {
    var x: Int
    var y: Int

    constructor(p: Point) {
        x = p.x
        y = p.y
    }

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor() {
        x = 0
        y = 0
    }

    fun reset() {
        x = 0
        y = 0
    }

    fun moveBy(x: Int, y: Int) {
        this.x += x
        this.y += y
    }

    val location: Point
        get() = Point(x, y)

    override fun toString(): String {
        return "$x,$y"
    }

    fun getDistance(p1: Point, p2: Point): Double {
        // returns the absolute distance between 2 points
        return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y)).toDouble())
    }
}