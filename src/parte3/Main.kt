package parte3

import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 10/06/20
 */
fun main(args: Array<String>) {
    val N = 4000

    // distance matrix with random numbers
    val distanceMatrix = Array(N) { IntArray(N) { 0 } }
    for (i in distanceMatrix.indices) {
        for (j in distanceMatrix[i].indices) {

            // fill only the upper triangle of the matrix
            if (i < j) {
                distanceMatrix[i][j] = (0..100).random()
            }
        }
    }

    val graph: BellmanFord<Int> = BellmanFord(distanceMatrix)
    println("ANTES:")
    println(graph)
    println("-------------------------------------------------\n\nDEPOIS:")
    val time = measureTimeMillis { graph.run() }
    println(graph)
    println("Elapsed time = ${TimeUnit.MILLISECONDS.toMinutes(time)} minutes.")
}