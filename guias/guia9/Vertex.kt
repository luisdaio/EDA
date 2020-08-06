package guia9

import guia5.LinkedList
import java.lang.StringBuilder

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 19/05/20
 */
class Vertex<T>(val key: T) {
    var color:Color? = null
    var adj = arrayListOf<Pair<Int, Vertex<T>>>()
    var d: Int? = null
    var pi: Vertex<T>? = null

    override fun toString(): String {
//        val s = StringBuilder()
//        s.append(this.key)
//        var sAdj = " "
//        for (adj in this.adj) {
//            sAdj += "${adj.second.key} "
//        }
//        s.append(sAdj)
//        return s.toString()
        return this.key.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (super.equals(other)) return true
        else if(other !is Vertex<*>) return false
        return (other.key == this.key && other.color == this.color && other.d == this.d &&
                other.pi == this.pi && other.adj == this.adj)
    }
}