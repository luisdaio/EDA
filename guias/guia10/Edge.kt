package guia10

import guia9.Vertex

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 26/05/20
 */
class Edge <T:Comparable<T>>(var weight: Int, var vertex0:Vertex<T>, var vertex1: Vertex<T>): Comparable<Edge<T>>{
    override fun compareTo(other: Edge<T>) = compareValuesBy(this, other, {it.weight}, {it.vertex0}, {it.vertex1})
    override fun toString(): String {
        return "[w= $weight, v1=${vertex0.key.toString()}, v2=${vertex1.key.toString()}]"
    }
}