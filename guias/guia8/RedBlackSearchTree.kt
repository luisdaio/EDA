package guia8

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 12/05/20
 */
class RedBlackSearchTree(n: Int) : BinarySearchTree(n) {
    private var color: IntArray = IntArray(n)
    private val BLACK = 0
    private val RED = 1
    val right: IntArray = this.memory.right
    val left: IntArray = this.memory.left
    private val parent: IntArray = this.memory.parent
    private fun leftRotate(x: Int) {
        val y = this.right[x]
        this.right[x] = this.left[y]
        if (this.left[y] != this.NIL) {
            this.parent[this.left[y]] = x
        }
        this.parent[y] = this.parent[x]
        when {
            this.parent[x] == this.NIL -> this.root = y
            x == this.parent[this.left[x]] -> this.parent[this.left[x]] = y
            else -> right[parent[x]] = y
        }
        this.left[y] = x
        this.parent[x] = y
    }

    private fun rightRotate(x: Int) {
        val y = this.left[x]
        this.left[x] = this.right[y]
        if (right[y] != this.NIL) {
            this.parent[this.right[y]] = x
        }
        this.parent[y] = this.parent[x]
        when {
            this.parent[x] == this.NIL -> this.root = y
            x == this.parent[this.right[x]] -> this.parent[this.right[x]] = y
            else -> this.left[this.parent[x]] = y
        }
        this.right[y] = x
        this.parent[x] = y
    }

    fun insertFixUp(z: Int) {
        var z = z
        var y: Int
        while (this.color[this.parent[z]] == this.RED) {
            if (this.parent[z] == this.left[this.parent[this.parent[z]]]) {
                y = this.right[this.parent[this.parent[z]]]
                if (this.color[y] == this.RED) {
                    this.color[this.parent[z]] = this.BLACK
                    this.color[y] = this.BLACK
                    this.color[this.parent[this.parent[z]]] = this.RED
                    z = this.parent[this.parent[z]]
                } else {
                    if (z == this.right[this.parent[z]]) {
                        z = this.parent[z]
                        this.leftRotate(z)
                    }
                    this.color[this.parent[z]] = this.BLACK
                    color[this.parent[this.parent[z]]] = this.RED
                    this.rightRotate(this.parent[this.parent[z]])
                }
            } else {
                y = left[parent[parent[z]]]
                if (color[y] == RED) {
                    color[parent[z]] = BLACK
                    color[y] = BLACK
                    color[parent[parent[z]]] = RED
                    z = parent[parent[z]]
                } else {
                    if (z == left[parent[z]]) {
                        z = parent[z]
                        this.rightRotate(z)
                    }
                    color[parent[z]] = BLACK
                    color[parent[parent[z]]] = RED
                    this.leftRotate(parent[parent[z]])
                }
            }
        } /* while */
        color[root] = BLACK
    }

    override fun insert(key: Int){
        super.insert(key)
        color[this.z] = this.RED
        println("z = ${this.z}")
        insertFixUp(this.z)
    }

    override fun toString(): String {
        val res = StringBuilder()
        res.append("\n")
        for (i in this.color.size - 1 downTo 0){
            res.append("$i -> color = ${if (color[i] == 0) "BLACK" else "RED"} \n")
        }
        return super.toString() + res
    }

}