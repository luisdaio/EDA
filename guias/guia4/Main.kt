package guia4

import Utils.Point


fun main(args: Array<String>) {
    println("Quick Sort!")
    var A = intArrayOf(2, 8, 7, 1, 3, 5 , 6, 4)
    println(A.contentToString())
//    val qs = QuickSort(A)
//    val qsIt = QuickSortIterative(A.copyOf())
    val qsLs = QuickSortList(A.toList())
    print(qsLs.sort().toString())

    var points = arrayOf(Point(0.0, 0.0, 0.0), Point(5.0, 5.0, 5.0), Point(10.0, 10.2, 10.3), Point(0.1, 0.1, 0.1))
    var sortedPoints = QuickSortPoints(points)
    printPoints(points)
}

fun printPoints(points : Array<Point>) {
    print("\n[")

    for (p in points) {
        System.out.format("%s, ", p.toString())
    }

    print("]\n")
}
