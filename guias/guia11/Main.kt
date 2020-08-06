package guia11

import guia10.Edge
import guia10.Graph
import guia10.Kruskal
import guia9.Vertex

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 02/06/20
 */

fun main(args: Array<String>) {
    println("Grafo!")
    val s = Vertex("s")
    val t = Vertex("t")
    val x = Vertex("x")
    val y = Vertex("y")
    val z = Vertex("z")
    val vertexes = arrayListOf(s, t, x, y, z)
    val vertexes1 = arrayListOf(s, t, x, y, z)

    val st = Edge(1, s, t)
    val sy = Edge(2, s, y)
    val tx = Edge(3, t, x)
    val xt = Edge(4, x, t)
    val ty = Edge(5, t, y)
    val tz = Edge(6, t, z)
    val yz = Edge(7, y, z)
    val zx = Edge(8, z, x)
    val zs = Edge(9, z, s)
    val edges = arrayListOf(st, sy, tx, xt, ty, tz, yz, zx, zs)
    val edges1 = arrayListOf(st, sy, tx, xt, ty, tz, yz, zx, zs)
    var bellmanFordGraph = BellmanFord(vertexes, edges)
    var dijkstraGraph = Dijkstra(vertexes1, edges1)

    for (v in vertexes) {
        println(v)
    }
    println()
    println(bellmanFordGraph)
    bellmanFordGraph.run(s)
    println(bellmanFordGraph)
    dijkstraGraph.run(s)
    println(dijkstraGraph)
}