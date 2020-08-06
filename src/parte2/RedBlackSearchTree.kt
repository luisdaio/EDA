package parte2

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 20/05/20
 */

/**
 * Red and Black Search Tree
 * Represents a Red and Black Search Tree of Strings (extends a normal BST).
 */
class RedBlackSearchTree(n: Int) : BinarySearchTree(n + 1) {
    private var color: IntArray = IntArray(n + 1)
    private val BLACK = 0
    private val RED = 1
    private val right: IntArray = this.memory.right
    private val left: IntArray = this.memory.left
    private val parent: IntArray = this.memory.parent

    /**
     * Rotates the given node and it's subtree to it's left
     * @param root starting node memory index
     */
    private fun leftRotate(root: Int) {
        val y = this.right[root]
        this.right[root] = this.left[y]
        if (this.left[y] != this.NIL) {
            this.parent[this.left[y]] = root
        }
        this.parent[y] = this.parent[root]
        when {
            this.parent[root] == this.NIL -> this.root = y
            root == this.left[this.parent[root]] -> this.left[this.parent[root]] = y
            else -> right[parent[root]] = y
        }
        this.left[y] = root
        this.parent[root] = y
    }

    /**
     * Rotates the given node and it's subtree to it's right
     * @param root starting node memory index
     */
    private fun rightRotate(root: Int) {
        val y = this.left[root]
        this.left[root] = this.right[y]
        if (right[y] != this.NIL) {
            this.parent[this.right[y]] = root
        }
        this.parent[y] = this.parent[root]
        when {
            this.parent[root] == this.NIL -> this.root = y
            root == this.right[this.parent[root]] -> this.right[this.parent[root]] = y
            else -> this.left[this.parent[root]] = y
        }
        this.right[y] = root
        this.parent[root] = y
    }

    /**
     * Maintains the red black tree's properties after the insertion of a new node
     * @param root starting node memory index
     */
    private fun insertFixUp(root: Int) {
        var root = root
        var y: Int
        while (this.color[this.parent[root]] == this.RED) {
            if (this.parent[root] == this.left[this.parent[this.parent[root]]]) {
                y = this.right[this.parent[this.parent[root]]]
                if (this.color[y] == this.RED) {
                    this.color[this.parent[root]] = this.BLACK
                    this.color[y] = this.BLACK
                    this.color[this.parent[this.parent[root]]] = this.RED
                    root = this.parent[this.parent[root]]
                } else {
                    if (root == this.right[this.parent[root]]) {
                        root = this.parent[root]
                        this.leftRotate(root)
                    }
                    this.color[this.parent[root]] = this.BLACK
                    color[this.parent[this.parent[root]]] = this.RED
                    this.rightRotate(this.parent[this.parent[root]])
                }
            } else {
                y = left[parent[parent[root]]]
                if (color[y] == RED) {
                    color[parent[root]] = BLACK
                    color[y] = BLACK
                    color[parent[parent[root]]] = RED
                    root = parent[parent[root]]
                } else {
                    if (root == left[parent[root]]) {
                        root = parent[root]
                        this.rightRotate(root)
                    }
                    color[parent[root]] = BLACK
                    color[parent[parent[root]]] = RED
                    this.leftRotate(parent[parent[root]])
                }
            }
        }
        color[this.root] = BLACK
    }

    /**
     * Inserts a new String in the Tree
     * @param key String value
     */
    override fun insert(key: String) {
        super.insert(key)
        color[this.z] = this.RED
        println("z = ${this.z}")
        insertFixUp(this.z)
    }

    override fun toString(): String {
        val res = StringBuilder()
        res.append("\n")
        for (i in this.color.size - 1 downTo 1) {
            res.append("$i -> color = ${if (color[i] == 0) "BLACK" else "RED"} \n")
        }
        return super.toString() + res
    }
}