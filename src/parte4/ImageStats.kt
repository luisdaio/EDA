package parte4

import org.opencv.core.Mat
import java.util.concurrent.Callable
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 17/06/20
 */

/**
 * Class ImageStats.
 * Calculates the mean and standard deviation of each color channel, given the image matrix.
 * @property startX starting x coordinate
 * @property endX ending x coordinate
 * @property startY starting y coordinate
 * @property endY starting y coordinate
 * @property imageMat image matrix
 */
open class ImageStats(private val startX: Int, private val endX: Int, private val startY: Int, private val endY: Int,
                      private val imageMat: Mat) {
    /**
     * Calculates the mean and standard deviation for each color channel of the image
     * @return array containing the mean and standard deviation for each color channel
     */
    fun calculate(): Array<Double>? {
        val size = 192 * 108
        val b = DoubleArray(size)
        val g = DoubleArray(size)
        val r = DoubleArray(size)
        var it = 0

        // adding the value of each color channel to the respective array
        for (x in startX until endX) {
            for (y in startY until endY) {
                try {
                    b[it] = imageMat.get(y, x)[0]
                    g[it] = imageMat.get(y, x)[1]
                    r[it] = imageMat.get(y, x)[2]
                    it++

                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
            }
        }

        // calculating the mean of each color channel
        val bMean = this.mean(b)
        val gMean = this.mean(g)
        val rMean = this.mean(r)
        return arrayOf(bMean, stdDeviation(b, bMean), gMean, stdDeviation(g, gMean), rMean, stdDeviation(r, rMean))
    }

    /**
     * Calculates the mean of an array of Doubles
     * @param array array containing the values
     * @return mean
     */
    private fun mean(array: DoubleArray): Double {
        return (array.sum() / array.size)
    }

    /**
     * Calculates the standard deviation of an array of Doubles
     * @param array array containing the values
     * @param mean mean value of the doubles in the array
     * @return standard deviation
     */
    private fun stdDeviation(array: DoubleArray, mean: Double): Double {
        val d: List<Double> = array.map { x -> (x - mean).pow(2.0) }
        return (sqrt(d.sum() / (array.size)))
    }
}

/**
 * Class ImageStatsCallable.
 * Callable for the ImageStats
 * @property startX starting x coordinate
 * @property endX ending x coordinate
 * @property startY starting y coordinate
 * @property endY ending y coordinate
 * @property imageMat image matrix
 */
class ImageStatsCallable(startX: Int, endX: Int, startY: Int, endY: Int,
                         imageMat: Mat) : ImageStats(startX, endX, startY, endY, imageMat),
        Callable<Array<Double>> {
    override fun call(): Array<Double>? {
        return super.calculate()
    }
}