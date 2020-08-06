import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val filename = "fibonacci_50_times.txt"
    val file = File(filename)
    file.printWriter().use { out ->
        for (i in 0..50) {
            out.println(" $i ${measureTimeMillis { fibIterative(i) }} ${measureTimeMillis { fibRecursive(i) }}")
        }
    }
}

fun fibIterative(n: Int): Int {
    return if (n == 0) {
        0
    } else {
        var fn1 = 1
        var fn2 = 1

        for (i in 3 until n + 1) {
            var temp = fn1
            fn1 += fn2
            fn2 = temp
        }
        fn1
    }
}

tailrec fun fibRecursive(n : Int) : Int {
    return if (n == 0) {
        0
    } else if (n == 1 || n == 2) {
        1
    } else {
        fibRecursive(n - 2) + fibRecursive(n - 1)
    }
}