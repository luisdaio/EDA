package parte3

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 10/06/20
 */

/**
 * Class Edge.
 * Represents an edge of a weighted graph.
 * @property weight weight associated with the edge
 * @property vertex0 starting vertex
 * @property vertex1 ending vertex
 */
class Edge<T : Comparable<T>>(var weight: Int, var vertex0: Vertex<T>, var vertex1: Vertex<T>) : Comparable<Edge<T>> {
    override fun compareTo(other: Edge<T>) = compareValuesBy(this, other, { it.weight }, { it.vertex0 }, { it.vertex1 })
    override fun toString(): String {
        return "[w= $weight, v1=${vertex0.key.toString()}, v2=${vertex1.key.toString()}]"
    }
}