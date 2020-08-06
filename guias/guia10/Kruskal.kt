package guia10

import guia9.Vertex
import java.util.*

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 26/05/20
 */

class Kruskal<T:Comparable<T>>(var graph: Graph<T>) {
    var sets = mutableListOf<TreeSet<Vertex<T>>>()
    fun minimumSpanningTree(): TreeSet<Edge<T>?> {
        var stree = TreeSet<Edge<T>?>()
        for (i in 0 until graph.vertexes.size) {
            sets.add(TreeSet<Vertex<T>>())
            sets[i].add(graph.vertexes[i])
        }
        graph.edges.sortedWith(compareBy { it.weight }).forEach { edge ->
            var index0 = findSet(edge.vertex0)
            var index1 = findSet(edge.vertex1)
            if (index0 != index1) {
                stree.add(edge)
                union(index0, index1)
            }
        }

        return stree
    }

    private fun findSet(vertex: Vertex<T>): Int? {
        var index:Int? = null
        for (i in 0 until sets.size) {
            if (sets[i].contains(vertex)) {
                index = i
            }
        }
        return index
    }

    private fun union(index0: Int?, index1: Int?) {
        sets[index0!!].addAll(sets[index1!!])
        sets.removeAt(index1!!)
    }
}