package parte3

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 10/06/20
 */

/**
 * Class Vertex.
 * Represents a vertex (or node) of a graph.
 * @property key vertex key
 */
class Vertex<T>(val key: T) : Comparable<Vertex<T>> {
    var adj = arrayListOf<Pair<Int, Vertex<T>>>()
    var d: Int? = null
    var pi: Vertex<T>? = null

    override fun toString(): String {
        val s = StringBuilder()
        s.append(this.key)
        var sAdj = " "
        for (adj in this.adj) {
            sAdj += "${adj.second.key} "
        }
        s.append(sAdj)
        return s.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (super.equals(other)) return true
        else if (other !is Vertex<*>) return false
        return (other.key == this.key && other.d == this.d &&
                other.pi == this.pi && other.adj == this.adj)
    }

    override fun compareTo(other: Vertex<T>) = compareValuesBy(this, other, { it.d }, { it.pi })
}