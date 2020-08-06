import java.io.File
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val range = 500000
    val random = Random
    val filename = "insertion_vs_bubble_times.txt"
    val file = File(filename)
    var array: IntArray
    var sortedArray1: IntArray
    var sortedArray2: IntArray

    file.printWriter().use { out ->

        for (i in 1..range) {

            var bubbleTime: Long = 0
            var insertionTime: Long = 0
            array = IntArray(i)

            for (j in array.indices) {

                array[j] = random.nextInt(0, range)
            }

            for (k in 1..50) {

                sortedArray1 = array.copyOf()
                sortedArray2 = array.copyOf()
                insertionTime += measureTimeMillis { insertionSort(sortedArray1) }
                bubbleTime += measureTimeMillis { bubbleSort(sortedArray2) }
            }

            insertionTime /= 50
            bubbleTime /= 50

            out.println("$i $insertionTime $bubbleTime")
        }
    }
}