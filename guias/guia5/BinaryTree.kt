package guia5

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 04/05/20
 */

class BinaryTree<T : Comparable<T>> {

    private var root: Node<T>? = null

    private fun <T : Comparable<T>> insert(current: Node<T>?, key: T): Node<T> {
        if (current == null) {
            return Node(key)
        }

        when {
            key < current.key -> {
                current.leftChild = insert(current.leftChild, key)
            }
            key > current.key -> {
                current.rightChild = insert(current.rightChild, key)
            }
            else -> {
                return current
            }
        }
        return current
    }

    fun insert(key: T) {
        root = insert(root, key)
    }

    private fun search(current: Node<T>?, key: T): Boolean {
        if (current == null) return false
        if (key == current.key) return true
        return if (key < current.key) {
            search(current.leftChild, key)
        } else {
            search(current.rightChild, key)
        }
    }

    fun search(key: T) {
        search(root, key)
    }

    private fun delete(current: Node<T>?, key: T): Node<T>? {
        when {
            current == null -> {
                return null
            }
            key == current.key -> {
                when {
                    (current.leftChild == null && current.rightChild == null) -> return null
                    current.rightChild == null -> return current.leftChild
                    current.leftChild == null -> return current.rightChild
                }
                var smallestValue = smallestValue(current.rightChild)
                current.key = smallestValue
                current.rightChild = delete(current.rightChild, smallestValue)
                return current
            }
            key < current.key -> {
                current.leftChild = delete(current.leftChild, key)
                return current
            }
            else -> {
                current.rightChild = delete(current.rightChild, key)
                return current
            }
        }
    }

    private fun smallestValue(root: Node<T>?): T {
        return if (root!!.leftChild == null) {
            root.key
        } else {
            smallestValue(root.leftChild)
        }
    }

    fun delete(key: T) {
        root = delete(root, key)
    }

    class Node<T : Comparable<T>>(var key: T) {
        var leftChild: Node<T>? = null
        var rightChild: Node<T>? = null
    }
}