package parte3

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 10/06/20
 */

/**
 * Class BellmanFord.
 * Extends the Graph class and implements the Bellman-Ford algorithm to find the shortest path between a node and all
 * the other nodes of a weighted graph.
 * @property vertexes List of vertexes
 * @property edges List of edges
 */
class BellmanFord<T : Comparable<T>>(vertexes: ArrayList<Vertex<T>>, edges: ArrayList<Edge<T>>) :
        Graph<T>(vertexes, edges) {
    private val d: MutableMap<Vertex<T>, Int> = mutableMapOf()
    private val pi: MutableMap<Vertex<T>, Vertex<T>?> = mutableMapOf()

    /**
     * Decrease, if possible, the distance of a node.
     * @param u path node
     * @param v destination node
     * @param w weight
     */
    private fun relax(u: Vertex<T>, v: Vertex<T>, w: Int) {
        if (d[v]!! > d[u]!! + w) {
            d[v] = d[u]!! + w
            pi[v] = u
        }
    }

    /**
     * Finds the shortest path between a node and all the other nodes in the graph.
     * @param s starting node (or vertex).
     * @return true if the the path contains all the nodes, false otherwise
     */
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

    /**
     * Finds the shortest path between a node and all the other nodes in the graph, starting at the first node added to
     * the graph.
     */
    fun run() {
        this.run(this.vertexes[0])
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

    /**
     * Secondary constructor.
     * Creates the a graph given it's distance matrix.
     * @param distanceMatrix the graph distance matrix
     */
    constructor(distanceMatrix: Array<IntArray>) : this(ArrayList(distanceMatrix.size), arrayListOf()) {
        var distance: Int
        vertexes.addAll(distanceMatrix.indices.map { Vertex(it as T) })
        for (i in distanceMatrix.indices) {
            for (j in distanceMatrix[i].indices) {
                if (i < j) {
                    distance = distanceMatrix[i][j]
                    if (distance != 0) {
                        edges.add(Edge(distance, vertexes[i], vertexes[j]))
                    }
                }
            }
        }
        for (vertex in this.vertexes) {
            d[vertex] = Int.MAX_VALUE
            pi[vertex] = null
        }
    }
}