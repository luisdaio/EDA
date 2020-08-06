package guia11

import guia10.Edge
import guia10.Graph
import guia9.Vertex

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 02/06/20
 */
class BellmanFord<T : Comparable<T>>(vertexes: ArrayList<Vertex<T>>, edges: ArrayList<Edge<T>>) :
    Graph<T>(vertexes, edges) {
    private val d: MutableMap<Vertex<T>, Int> = mutableMapOf()
    private val pi: MutableMap<Vertex<T>, Vertex<T>?> = mutableMapOf()

    private fun relax(u: Vertex<T>, v: Vertex<T>, w: Int) {
        if (d[v]!! > d[u]!! + w) {
            d[v] = d[u]!! + w
            pi[v] = u
        }
    }

    fun run(s: Vertex<T>): Boolean {
        d[s] = 0
        for (i in 1 until this.vertexes.size) {
            for (edge in this.edges) {
                val u = edge.vertex0
                val v = edge.vertex1
                val w = edge.weight
                relax(u, v, w)
            }
        }

        for (edge in this.edges) {
            val u = edge.vertex0
            val v = edge.vertex1
            val w = edge.weight
            if (d[v]!! > d[u]!! + w) return false
        }
        return true
    }

    override fun toString(): String {
        val s: StringBuilder = StringBuilder()
        for (v in this.vertexes) s.append("v = ${v.key} pi = ${pi[v]} \n")
        return s.toString()
    }

    init {
        for (vertex in this.vertexes) {
            d[vertex] = Int.MAX_VALUE
            pi[vertex] = null
        }
    }
}