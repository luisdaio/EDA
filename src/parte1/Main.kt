package parte1

import org.opencv.core.Core
import org.opencv.imgcodecs.Imgcodecs
import java.io.File
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 */

fun main() {
    /**
     * This part of the program corresponds to the test of the Bucket Sort algorithm execution time.
     * To run it, uncomment lines 13 to 26, comment everything else and run the main function.
     */
    val bucketSortTimes = "src/parte1/bucketSortTimes.txt"
    val file = File(bucketSortTimes)
    val tester = BucketSortTester()
    val start = 10000
    val end = 100000
    val ke = abs(1.0 / (tester.sortingTime(end) - tester.sortingTime(start))) * 100.0
    val kt = abs(1.0 /((end.toLong() * end.toLong()) - (start.toLong() * start.toLong()))) * 100.0

    file.printWriter().use { out ->
        for (i in start..end step 1000) {
            out.println("$i ${tester.meanTimeWithError(i, 500) * ke} ${((i.toLong() * i.toLong()) * kt)}")
        }
    }

    /**
     * This part of the program corresponds to sorting the count of the number of pixels for each gray level of an image.
     * To run it, uncomment lines 32 to 52, comment everything else and run the main function.
     */
//    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
//    val imagePath = "/home/img.jpg"
//    val filePath = "src/parte1/orderedPixelCount.txt"
//    var orderedPixelCountFile = File(filePath)
//    val imgMat = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_GRAYSCALE)
//    val pixelsPerLevel = Array(256){ i -> PixelsPerLevel(i,0) }
//    val nPixels = imgMat.cols() * imgMat.rows()
//
//    for (row in 0 until imgMat.rows()) {
//        for (col in 0 until imgMat.cols()) {
//            pixelsPerLevel[imgMat.get(row, col)[0].roundToInt()].nPixels++
//        }
//    }
//    BucketSort.sort(pixelsPerLevel, 100)
//
//    orderedPixelCountFile.printWriter().use { out ->
//        pixelsPerLevel.forEach {p ->
//            var percentage: Double = (p.nPixels * 100.0) / nPixels
//            out.println("${p.level} ${p.nPixels} $percentage")
//        }
//    }
}