package parte4

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import java.io.File
import java.util.concurrent.Executors
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 16/06/20
 */

/**
 * Driver function.
 */
fun main(args: Array<String>) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    val imagePath = if (args.isEmpty()) "/home/img.jpg" else args[0]
    val statsFile = File("src/parte4/imageStats.txt")
    val imgMat = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_COLOR)
    var stats: Array<Array<Double>>? = null
    val nIterations = 100
    val times = Array(nIterations) { 0.0 }
    val timesWithThreads = Array(nIterations) { 0.0 }

    // calculate the execution times with and without threads
    for (i in 0 until nIterations) {
        times[i] = measureTimeMillis { stats = run(imgMat) }.toDouble()
        timesWithThreads[i] = measureTimeMillis { runWithThreads(imgMat) }.toDouble()
    }

    // save the results in a file
    statsFile.printWriter().use { out ->
        for (i in stats!!.indices) {
            out.println("$i ${stats!![i][0]} ${stats!![i][1]} ${stats!![i][2]} ${stats!![i][3]} ${stats!![i][4]}" +
                    " ${stats!![i][5]}")
        }
    }

    // calculate the relative error of the execution times
    val relativeError = relativeError(times)
    val relativeErrorWithThreads = relativeError(timesWithThreads)

    // print the results
    println("Tempo médio : ${mean(times)} ns, Erro: ${String.format("%.1f", relativeError)}% \n" +
            "Tempo médio com threads: ${mean(timesWithThreads)} ns, Erro: ${String.format("%.1f", relativeErrorWithThreads)}%")
}

/**
 * Calculates the image stats (mean and standard deviation for each color channel) in blocks of 108x192 px
 * @param imgMat opencv's matrix that represents the image
 */
fun run(imgMat: Mat): Array<Array<Double>> {
    var block = 0
    val width = imgMat.rows() / 108
    val height = imgMat.cols() / 192
    val stats = Array(100) { Array(6) { 0.0 } }

    for (i in 0 until width) {
        for (j in 0 until height) {
            stats[block++] = ImageStats(i * 192, (i + 1) * 192, j * 108, (j + 1) * 108, imgMat).calculate()!!
        }
    }
    return stats
}

/**
 * Calculates the image stats (mean and standard deviation for each color channel),
 * in blocks of 108x192 px using an Executor
 * @param imgMat opencv's matrix that represents the image
 */
fun runWithThreads(imgMat: Mat) {

    val width = (imgMat.rows() / 108)
    val height = (imgMat.cols() / 192)
    val nThreads = Runtime.getRuntime().availableProcessors()
    val executor = Executors.newFixedThreadPool(nThreads)
    var startX: Int
    var endX: Int
    var startY: Int
    var endY: Int

    // for each block add a callable to the Executor
    for (j in 0 until width) {
        for (k in 0 until height) {
            startX = j * 192
            endX = (j + 1) * 192
            startY = k * 108
            endY = (k + 1) * 108
            executor.submit(ImageStatsCallable(startX, endX, startY, endY, imgMat))
        }
    }

    // wait until all threads are finished
    executor.shutdown()
}

/**
 * Calculates the mean of an array of Doubles
 * @param array array containing the values
 * @return mean
 */
private fun mean(array: Array<Double>): Double {
    return (array.sum() / array.size)
}

/**
 * Calculates the standard deviation of an array of Doubles
 * @param array array containing the values
 * @return standard deviation
 */
private fun stdDeviation(array: Array<Double>): Double {
    val mean = mean(array)
    val d: List<Double> = array.map { x -> (x - mean).pow(2.0) }
    return (sqrt(d.sum() / (array.size)))
}

/**
 * Calculates the relative error of an array in percentage.
 * @param array array containing the values
 * @return relative error
 */
private fun relativeError(array: Array<Double>): Double {
    val stdDeviation = stdDeviation(array)
    val mean = mean(array)
    return (stdDeviation / mean) * 100.0
}