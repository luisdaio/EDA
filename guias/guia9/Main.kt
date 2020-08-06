package guia9

import java.util.*

fun main(args: Array<String>) {
    println("Grafo!")
    val s = Vertex("s")
    val t = Vertex("t")
    val x = Vertex("x")
    val y = Vertex("y")
    val z = Vertex("z")
    val vertexes = arrayListOf(s, t, x, y, z)

    val st = Pair(0, Pair(s, t))
    val sy = Pair(0, Pair(s, y))
    val tx = Pair(0, Pair(t, x))
    val xt = Pair(0, Pair(x, t))
    val ty = Pair(0, Pair(t, y))
    val tz = Pair(0, Pair(t, z))
    val yz = Pair(0, Pair(y, z))
    val zx = Pair(0, Pair(z, x))
    val zs = Pair(0, Pair(z, s))

    val edges = arrayListOf(st, sy, tx, xt, ty, tz, yz, zx, zs)
    var graph = Graph(vertexes, edges)

    for (v in vertexes) {
        println(v)
    }
    graph.printPath(s, x)
    println()
    println(graph)
    graph.topologicalSort()
}