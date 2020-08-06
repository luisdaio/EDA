package parte3

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 10/06/20
 */

/**
 * Class Graph.
 * Represents a graph.
 * @property vertexes List of vertexes (or nodes).
 * @property edges List of edges
 */
open class Graph<T : Comparable<T>>(val vertexes: ArrayList<Vertex<T>>, val edges: ArrayList<Edge<T>>) {

    /**
     * Creates the edges between the nodes.
     */
    private fun createEdges() {
        for (edge in this.edges) {
            val w = edge.weight
            val v1 = edge.vertex0
            val v2 = edge.vertex1
            v1.adj.add(Pair(w, v2))
        }
    }

    override fun toString(): String {
        var s = ""
        for (v in vertexes) s = s + "v = " + v.key + " pi = " + v.pi + "\n"
        return s
    }

    init {
        this.createEdges()
    }
}