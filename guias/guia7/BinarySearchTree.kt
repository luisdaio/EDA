package guia7

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 05/05/20
 */
class BinarySearchTree(n: Int) {
    var memory = BinaryTreeMemory(n)
    var NIL = 0
    var root = NIL
    var z = 0

    fun insert(key: Int) {
        this.z = memory.allocateObject()
        this.memory.key[z] = key
        var y: Int
        var x = this.root
        while (x != this.NIL) {
            y = x
            x = if (this.memory.key[z] < this.memory.key[x]) {
                this.memory.left[x]
            } else {
                this.memory.right[x]
            }
            this.memory.parent[z] = y

            when {
                y == this.NIL -> {
                    this.root = z
                }
                this.memory.key[z] < this.memory.key[y] -> {
                    this.memory.left[y] = z
                }
                else -> {
                    this.memory.right[y] = z
                }
            }
        }
    }

    fun inOrderTreeWalk(x: Int) {
        if (memory.key[x] != this.NIL) {
            inOrderTreeWalk(memory.left[x])
            print("${memory.key[x]}")
            inOrderTreeWalk(memory.right[x])
        }
    }

    fun treeSearch(key: Int): Int {
        return this.treeSearch(this.root, key)
    }

    private fun treeSearch(root: Int, key: Int): Int {
        return if (root == NIL || key == memory.key[root]) {
            root
        } else if (key < memory.key[root]) {
            treeSearch(memory.left[root], key)
        } else {
            treeSearch(memory.right[root], key)
        }
    }

    private fun iterativeTreeSearch(root: Int, key: Int): Int {
        var searchRoot = root
        while (searchRoot != this.NIL && memory.key[searchRoot] != key) {
            searchRoot = if (key < memory.key[searchRoot]) {
                memory.left[searchRoot]
            } else {
                memory.right[searchRoot]
            }
        }
        return searchRoot
    }

    fun iterativeTreeSearch(key: Int): Int {
        return this.iterativeTreeSearch(this.root, key)
    }

    private fun treeMinimum(root: Int): Int {
        var searchRoot = root
        while (memory.left[searchRoot] != this.NIL) {
            searchRoot = memory.left[searchRoot]
        }
        return searchRoot
    }

    fun treeMinimum(): Int {
        return this.treeMinimum(this.root)
    }

    fun treeMaximum(): Int {
        var searchRoot = this.root
        while (memory.right[searchRoot] != this.NIL) {
            searchRoot = memory.right[searchRoot]
        }
        return searchRoot
    }

    fun treeSucessor(root: Int): Int {
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

    private fun transplant(u: Int, v: Int) {
        when {
            this.memory.parent[u] == this.NIL -> {
                this.root = v
            }
            u == this.memory.left[this.memory.parent[u]] -> {
                this.memory.left[this.memory.parent[u]] = v
            }
            else -> {
                this.memory.right[this.memory.parent[u]] = v
            }
        }

        if (v != this.NIL) {
            memory.parent[v] = this.memory.parent[u]
        }
    }

    fun delete(z: Int) {
        when (this.NIL) {
            this.memory.left[z] -> {
                this.transplant(z, this.memory.right[z])
            }
            this.memory.right[z] -> {
                this.transplant(z, this.memory.left[z])
            }
            else -> {
                val y = this.treeMinimum(this.memory.right[z])
                if (this.memory.parent[y] != z) {
                    this.transplant(y, this.memory.right[y])
                    this.memory.right[y] = this.memory.right[z]
                    this.memory.parent[this.memory.right[y]] = y
                }
                this.transplant(z, y)
                this.memory.left[y] = this.memory.left[z]
                this.memory.parent[this.memory.left[y]] = y
            }
        }
    }
}