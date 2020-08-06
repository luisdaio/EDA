package parte2

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 20/05/20
 */

/**
 * Binary Search Tree
 * Represents a Binary Search Tree of Strings.
 */
open class BinarySearchTree(n: Int) {
    protected var memory: BinaryTreeMemory = BinaryTreeMemory(n)
    protected val NIL = 0
    protected var root = this.NIL
    protected var z = 0

    /**
     * Inserts a new String in the Tree
     * @param key String value
     */
    open fun insert(key: String) {
        z = memory.allocateObject()
        memory.key[z] = key
        var y = NIL
        var x = this.root
        while (x != NIL) {
            y = x
            x = if (memory.key[z]!! < memory.key[x]!!) {
                memory.left[x]
            } else {
                memory.right[x]
            }
        }
        memory.parent[z] = y
        when {
            y == NIL -> this.root = z
            memory.key[z]!! < memory.key[y]!! -> memory.left[y] = z
            else -> {
                memory.right[y] = z
            }
        }
    }

    /**
     * Traverses the tree in crescent, in this case alphabetical, order.
     * @param root root tree node
     */
    fun inOrderTreeWalk(root: Int) {
        if (memory.key[root] != null) {
            inOrderTreeWalk(memory.left[root])
            print("${memory.key[root]}")
            inOrderTreeWalk(memory.right[root])
        }
    }

    /**
     * Tree search helper
     */
    fun treeSearch(key: String): Int {
        return this.treeSearch(this.root, key)
    }

    /**
     * Recursively searches for the presence of a String in the tree, starting a the given node
     * @param root starting node memory index
     * @param key String value
     */
    private fun treeSearch(root: Int, key: String): Int {
        return if (root == NIL || key == memory.key[root]) {
            root
        } else if (key < memory.key[root]!!) {
            treeSearch(memory.left[root], key)
        } else {
            treeSearch(memory.right[root], key)
        }
    }

    /**
     * Iteratively searches for the presence of a String in the tree, starting a the given node
     * @param root starting node memory index
     * @param key String value
     * @return memory index
     */
    private fun iterativeTreeSearch(root: Int, key: String): Int {
        var searchRoot = root
        while (searchRoot != this.NIL && memory.key[searchRoot] != key) {
            searchRoot = if (key < memory.key[searchRoot]!!) {
                memory.left[searchRoot]
            } else {
                memory.right[searchRoot]
            }
        }
        return searchRoot
    }

    /**
     * Iterative tree search helper
     * @param key String value
     * @return memory index
     */
    fun iterativeTreeSearch(key: String): Int {
        return this.iterativeTreeSearch(this.root, key)
    }

    /**
     * Searches for the leftmost String in the tree, starting at the given node
     * @param root starting node memory index
     * @return memory index
     */
    private fun treeMinimum(root: Int): Int {
        var searchRoot = root
        while (memory.left[searchRoot] != this.NIL) {
            searchRoot = memory.left[searchRoot]
        }
        return searchRoot
    }

    /**
     * Tree minimum helper
     * @return memory index
     */
    fun treeMinimum(): Int {
        return this.treeMinimum(this.root)
    }


    /**
     * Searches for the rightmost String in the tree, starting at the given node
     * @param root starting node memory index
     * @return memory index
     */
    private fun treeMaximum(root: Int): Int {
        var searchRoot = root
        while (memory.right[searchRoot] != this.NIL) {
            searchRoot = memory.right[searchRoot]
        }
        return searchRoot
    }

    /**
     * Tree maximum helper
     * @return memory index
     */
    fun treeMaximum(): Int {
        return this.treeMaximum(this.root)
    }

    /**
     * Returns the successor node of the given node
     * @param root starting node memory index
     * @return memory index
     */
    fun treeSuccessor(root: Int): Int {
        var searchRoot = root
        if (this.memory.right[searchRoot] != this.NIL) {
            return this.treeMinimum(searchRoot)
        }
        var parent = this.memory.parent[searchRoot]
        while (parent != this.NIL && searchRoot == memory.right[parent]) {
            searchRoot = parent
            parent = memory.parent[parent]
        }
        return parent
    }

    /**
     * Returns the predecessor node of the given node
     * @param root starting node memory index
     * @return memory index
     */
    fun treePredecessor(root: Int): Int {
        var searchRoot = root
        if (this.memory.left[searchRoot] != this.NIL) {
            return this.treeMaximum(searchRoot)
        }
        var parent = this.memory.parent[searchRoot]
        while (parent != this.NIL && searchRoot == memory.left[parent]) {
            searchRoot = parent
            parent = memory.parent[parent]
        }
        return parent
    }

    /**
     * Transplants the subtrees of the given nodes
     * @param root1 memory index of first node
     * @param root2 memory index of second node
     */
    private fun transplant(root1: Int, root2: Int) {
        when {
            this.memory.parent[root1] == this.NIL -> {
                this.root = root2
            }
            root1 == this.memory.left[this.memory.parent[root1]] -> {
                this.memory.left[this.memory.parent[root1]] = root2
            }
            else -> {
                this.memory.right[this.memory.parent[root1]] = root2
            }
        }

        if (root2 != this.NIL) {
            memory.parent[root2] = this.memory.parent[root1]
        }
    }

    override fun toString(): String {
        return ("${this.memory} root -> ${if (this.root == 0) "nil" else this.root}")
    }
}