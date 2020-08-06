package Utils

import kotlin.math.pow
import kotlin.math.sqrt

class Point(private val x: Double, private val y: Double, private val z: Double) {
    var distToOrigin = 0.0
        private set

    private fun computeDistToOrigin() {
        distToOrigin = sqrt(
            x.pow(2.0) + y.pow(2.0) + z.pow(2.0)
        )
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    init {
        computeDistToOrigin()
    }

}