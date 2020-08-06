package guia10

import guia9.Vertex

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 26/05/20
 */

fun main(args: Array<String>) {
    println("Grafo!")
    val s = Vertex("s")
    val t = Vertex("t")
    val x = Vertex("x")
    val y = Vertex("y")
    val z = Vertex("z")
    val vertexes = arrayListOf(s, t, x, y, z)

    val st = Edge(30, s, t)
    val sy = Edge(2, s, y)
    val tx = Edge(3, t, x)
    val xt = Edge(4, x, t)
    val ty = Edge(5, t, y)
    val tz = Edge(10, t, z)
    val yz = Edge(7, y, z)
    val zx = Edge(8, z, x)
    val zs = Edge(9, z, s)

    val edges = arrayListOf(st, sy, tx, xt, ty, tz, yz, zx, zs)
    var graph = Graph(vertexes, edges)

    for (v in vertexes) {
        println(v)
    }
    graph.printPath(s, x)
    println()
    println(graph)
    graph.topologicalSort()
    val kruskal = Kruskal(graph)
    kruskal.minimumSpanningTree().forEach { println(it) }
}