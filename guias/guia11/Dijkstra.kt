package guia11

import guia10.Edge
import guia10.Graph
import guia9.Vertex
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 02/06/20
 */
class Dijkstra<T : Comparable<T>>(vertexes: ArrayList<Vertex<T>>, edges: ArrayList<Edge<T>>) :
    Graph<T>(vertexes, edges) {
    private val d: MutableMap<Vertex<T>, Int> = mutableMapOf()
    private val pi: MutableMap<Vertex<T>, Vertex<T>?> = mutableMapOf()
    var q: PriorityQueue<Vertex<T>>
    private fun relax(u: Vertex<T>, v: Vertex<T>, w: Int) {
        if (d[v]!! > d[u]!! + w) {
            d[v] = d[u]!! + w
            pi[v] = u
            q.remove(v)
            q.add(v)
        }
    }

    fun run(s: Vertex<T>){
        d[s] = 0
        while (q.peek() != null) {
            val u = q.poll()
            for (adjacency in u.adj) {
                relax(u, adjacency.second, adjacency.first)
            }
        }
    }

    override fun toString(): String {
        val s: StringBuilder = StringBuilder()
        for (v in this.vertexes) s.append("v = ${v.key} pi = ${pi[v]} \n")
        return s.toString()
    }

    class VertexComparator<T : Comparable<T>>(private val d: MutableMap<Vertex<T>, Int>) : Comparator<Vertex<T>> {
        override fun compare(p0: Vertex<T>?, p1: Vertex<T>?): Int {
            return d[p0]!!.compareTo(d[p1]!!)
        }
    }

    init {
        for (vertex in this.vertexes) {
            d[vertex] = Int.MAX_VALUE
            pi[vertex] = null
        }
        q = PriorityQueue(this.vertexes.size, VertexComparator(d))
        q.addAll(this.vertexes)
    }
}