package guia9

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 19/05/20
 */
class Graph<T>(
    val vertexes: ArrayList<Vertex<T>>,
    private val edges: ArrayList<Pair<Int, Pair<Vertex<T>, Vertex<T>>>>
) {

    private var time = 0
    val INFINITY = Float.POSITIVE_INFINITY

    fun createEdges() {
        for (edge in this.edges) {
            val w = edge.first
            val v1 = edge.second.first
            val v2 = edge.second.second
            v1.adj.add(Pair(w, v2))
        }
    }

    fun breadthSearch(u: Vertex<T>) {
        var u1 = u
        var queue = Queue<Vertex<T>>(vertexes.size)

        for (u in this.vertexes) {
            u.color = Color.WHITE
            u.d = this.INFINITY.toInt()
        }
        u1.color = Color.GREY
        u1.d = 0
        queue.enqueue(u1)

        while (queue.head != queue.tail) {
            u1 = queue.dequeue()
//            print("${u1.key} ")
            for (v in u1.adj) {
                if (v.second.color == Color.WHITE) {
                    v.second.color = Color.GREY
                    v.second.d = u1.d!! + 1
                    v.second.pi = u1
                    queue.enqueue(v.second)
                }
            }
            u1.color = Color.BLACK
        }
//        println()
    }

    fun printPath(s: Vertex<T>, v: Vertex<T>) {
//        this.breadthSearch(vertexes[0])
        this.depthSearch()
        when {
            s.equals(v) -> print(s)
            v.pi == null -> print("no path from $s to $v exists")
            else -> {
                printPath(s, v.pi!!); print(v)
            }
        }
    }

    fun depthSearch() {
        for (u in vertexes) {
            u.color = Color.WHITE
            u.pi = null
        }
        this.time = 0
        for (u in vertexes) {
            this.depthSearch(u)
        }
    }

    private fun depthSearch(u: Vertex<T>) {
        this.time += 1
        u.d = this.time
        u.color = Color.GREY
        for (v in u.adj) {
            if (v.second.color == Color.WHITE) {
                v.second.pi = u
                this.depthSearch(v.second)
            }
        }
        u.color = Color.BLACK
        this.time += 1
        u.time = this.time
    }

    fun topologicalSort() {
        val pile = Pile<Vertex<T>>(vertexes.size)
        for (u in vertexes) {
            u.color = Color.WHITE
            u.pi = null
        }
        this.time = 0

        for (u in vertexes) {
            this.depthSearch(u)
            pile.push(u)
        }

        while (!pile.stackEmpty()){
            print("${pile.pop()} ")
        }
    }

    init {
        this.createEdges()
    }

    override fun toString(): String {
        var s = ""
        for (v in vertexes) s = s + "v = " + v.key + " pi = " + v.pi + "\n"
        return s
    }
}