package guia12

import java.lang.management.ManagementFactory

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 16/06/20
 */

fun main() {
    val numProcessors: Int = Runtime.getRuntime().availableProcessors()
    println("Número de processadores disponíveis = $numProcessors")

    for (i in 0 until numProcessors) {
        var dT = DerivativeThread()
        dT.start()
    }
    println()

    for (i in 0 until 13) {
//        var f = Factorial(i)
        var f = Fibonacci(i)
        var t = Thread(f, i.toString())
        t.start()

        try {
            t.join()
        } catch (ie: InterruptedException) {
            println(ie)
        }
        var nanos: Long = ManagementFactory.getThreadMXBean().getThreadCpuTime(t.id)
        println("Thread - ${t.name} Fib: ${f.getRes()} Time = $nanos ns")
    }
}
