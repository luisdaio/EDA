package parte1

import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 */

/**
 * Class BucketSortTester.
 * Tests the computational complexity of the sorting algorithm Bucket Sort.
 */
class BucketSortTester() {

    /**
     * Returns an array with random positive integers.
     * @param size size of the array
     */
    private fun arrayWithRandomInts(size: Int): IntArray {
        val random = Random
        return IntArray(size) { random.nextInt(Int.MAX_VALUE) }
    }

    /**
     * Returns the average bucket sort execution time with less than 1% error (depending on the number of samples).
     * @param nElements number of elements to sort
     * @param nSamples number of times to sort the array and compute the mean and standard deviation
     * @return the average execution time
     */
    fun meanTimeWithError(nElements: Int, nSamples: Int): Double {
        val times = DoubleArray(nSamples)
        for (i in 0 until nSamples) {
            times[i] = sortingTime(nElements)
        }
        val meanAndStDeviation = getMeanAndStDeviation(times)
        val relativeError = getRelativeError(meanAndStDeviation)
        println("N: $nElements \nSamples: $nSamples\nRelative error: $relativeError\n ")
        return meanAndStDeviation.first
    }

    /**
     * Returns a Pair that contains the mean and standard deviation of the input array
     * @param times input array
     * @return A pair containing the mean and standard deviation
     */
    private fun getMeanAndStDeviation(times: DoubleArray): Pair<Double, Double> {
        var standardDeviation = 0.0
        val mean = getMean(times)
        for (time in times) {
            standardDeviation += (time - mean).pow(2)
        }
        standardDeviation = sqrt(standardDeviation / times.size)
        return Pair(mean, standardDeviation)
    }

    /**
     * Returns the mean value for.
     * @param array array containing the values
     * @return mean value
     */
    private fun getMean(array: DoubleArray): Double {
        val mean = array.sum()
        return mean / array.size
    }

    /**
     * Returns the percentage relative error.
     * @param meanAndStDeviation pair that contains the mean and standard deviation
     * @return percentage relative error
     */
    private fun getRelativeError(meanAndStDeviation: Pair<Double, Double>): Double {
        return (meanAndStDeviation.second / meanAndStDeviation.first) * 100.0
    }

    /**
     * Returns the bucket sort execution time for an array with random integers, in milliseconds.
     * @param nElements number of elements to sort
     * @return execution time
     */
    fun sortingTime(nElements: Int): Double {
        val arrayWithRandomInts = arrayWithRandomInts(nElements)
        return measureTimeMillis { BucketSort.sort(arrayWithRandomInts) }.toDouble()
    }
}