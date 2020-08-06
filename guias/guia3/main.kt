package guia3

import java.io.File
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val range = 50000
    val random = Random
    val filename = "heapSort-times.txt"
    val file = File(filename)
    var array: IntArray
    var sortedArray1: IntArray

    file.printWriter().use { out ->

        for (i in 1..range) {

            var heapTime: Long = 0
            array = IntArray(i)

            for (j in array.indices) {

                array[j] = random.nextInt(0, range)
            }

            for (k in 1..50) {

                sortedArray1 = array.copyOf()
                heapTime += measureTimeMillis { HeapSort(sortedArray1) } / 1000
            }

            heapTime /= 50

            out.println("$i $heapTime")
        }
    }
}