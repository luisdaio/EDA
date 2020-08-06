package guia2

import Utils.Utils
import java.io.FileWriter
import java.io.IOException
import kotlin.math.floor

class MergeSort2 {
    fun merge(A: IntArray, p: Int, q: Int, r: Int) {
        val n1 = q - p + 1
        val n2 = r - q
        val L = IntArray(n1 + 1)
        val R = IntArray(n2 + 1)
        for (i in 0 until n1) {
            L[i] = A[p + i]
        }
        for (j in 0 until n2) {
            R[j] = A[q + j + 1]
        }
        L[n1] = Int.MAX_VALUE
        R[n2] = Int.MAX_VALUE
        var i = 0
        var j = 0
        for (k in p..r) {
            if (L[i] <= R[j]) {
                A[k] = L[i++]
            } else {
                A[k] = R[j++]
            }
        }
    }

    tailrec fun run(A: IntArray, p: Int, r: Int) {
        if (p < r) {
            val q = floor((p + r) / 2.0).toInt()
            run(A, p, q)
            run(A, q + 1, r)
            merge(A, p, q, r)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val m = MergeSort2()
            val filename = "src/guia2/mergeSortTimes.txt"
            try {
                val fileWriter = FileWriter(filename)
                var a = 1000000
                while (a <= 30000000) {
                    val ints: IntArray = Utils.arrayWithRandomInts(a)
                    var timeElapsed: Long = 0
                    for (i in 0..49) {
                        val clone = ints.clone()
                        val startTime = System.nanoTime()
                        m.run(clone, 0, ints.size - 1)
                        val endTime = System.nanoTime()
                        timeElapsed += (endTime - startTime) / 1000000000
                    }
                    timeElapsed /= 50
                    fileWriter.append("$a $timeElapsed\n")
                    a += 1000000
                }
                fileWriter.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}