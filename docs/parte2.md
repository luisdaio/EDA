<!--
    Relizado por: Luís Fernandes
    N.º: 17186
    E-mail : 17186@stu.ipbeja.pt
-->

# Estruturas de Dados Simples
## 1. Introdução
Nesta secção do projeto pretende-se estudar uma estrutura de dados simples designada por __árvore de pesquisa _red-black___. Esta estrutura de dados consiste numa árvore de pesquisa binária balanceada em que cada nó apresenta mais um _bit_ de armanezamento designado por RED ou BLACK. Para além das propriedades de uma árvore de pesquisa binária comum, apresenta as seguintes:

+ Todos os nós têm uma cor, vermelho ou preto;
* O nó raíz é preto;
+ Todas as folhas (NIL) são pretas;
* Se um nó vermelho tiver filhos, os filhos são pretos;
+ Para todos os nós, todos caminhos simples entre esse nó e as suas folhas descendentes contêm o mesmo número de nós pretos.

Uma árvore de pesquisa binária normal de altura $h$, consegue implementar as operações básicas de conjuntos dinâmicos com ordem de crescimento do tempo de execução $O(h)$. Ou seja, quanto maior for a altura da árvore, maior é o tempo de execução. Por outro lado, derivado às suas propriedades, a árvore _red black_ apresenta uma ordem de crescimento do tempo de execução $O(log(n))$, nos priores casos, para as mesmas operações.

De modo a realizar o estudo do funcionamento desta estrutura de dados, programou-se a mesma com recurso à linguagem de programação Kotlin. Posteriormente desenvolveu-se um programa que recebe um texto e insere na árvore cada uma das palavras.

## 2. Código desenvolvido

Todo o código desenvolvido neste trabalho é baseado no código disponibilizado pelo docente nos guias das aulas laboratoriais.

### 2.1. Classe _Pile_

A classe abaixo representa a estrutura de dados designada por pilha (_pile_ ou _stack_ em inglês). Esta estrutura de dados implementa uma política **LIFO** (_last-in_, *first-out*), em que o elemento a remover é o que foi inserido mais recentemente. 

```kotlin

/**
 * Pile
 * Represents a pile (also known as stack).
 */
class Pile(n: Int) {
    var n = 0
    private var top: Int = -1
    private var pile:IntArray

    /**
     * Returns the state of the stack.
     * @return true if the stack is empty, false otherwise
     */
    private fun stackEmpty(): Boolean {
        return top == -1
    }

    /**
     * Inserts a new element in the stack.
     * @param x integer value to insert
     */
    fun push(x: Int) {
        top += 1
        pile[top] = x
    }

    /**
     * Removes the last element in the stack (LIFO policy).
     * @return the last element in the stack, 0 if the stack is empty
     */
    fun pop(): Int {
        return if (stackEmpty()) {
            println("underflow")
            0
        } else {
            top = top - 1
            pile[top + 1]
        }
    }

    override fun toString(): String {
        val s = StringBuilder()
        for (i in pile) {
            s.append(i).append(" ")
        }
        return s.toString()
    }

    init {
        this.n = n
        this.top = - 1
        pile = IntArray(this.n)
    }
}
```
### 2.2. Classe _Binary Tree Memory_

Esta classe pretende representar a memória utilizada para guardar os elementos da árvore. A sua gestão é feita utilizando uma _Pile_, que através do método _allocateObject_ indica a posição da memória aonde será guardado o próximo objecto, neste caso a próxima _String_. Para além de guardar a chave correspondente a cada nó, esta representação de memória utiliza tabelas auxiliares para guardar ponteiros para os filhos (_left_ e *right*),  e para o nó progenitor (_parent_).

```kotlin

/**
 * Binary Tree Memory
 * Represents the memory for a binary search tree, using a pile (a.k.a. stack).
 */
class BinaryTreeMemory(size: Int) {
    var parent: IntArray
    var key: Array<String?>
    var left: IntArray
    var right: IntArray
    private val NIL = 0
    private var pile: Pile

    /**
     * Allocates a space in memory.
     */
    fun allocateObject(): Int {
        val x = pile.pop()
        return if (x == 0) {
            0
        } else {
            x
        }
    }

    /**
     * Updates the memory's "free list".
     */
    private fun freeObject(x: Int) {
        this.pile.push(x)
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("\n")
        for (k in parent.size - 1 downTo 1) {
            s.append(
                "$k -> [parent = ${if (parent[k] == 0) "nil" else parent[k]}," +
                        " key = ${key[k]}, left = ${if (left[k] == 0) "nil" else left[k]}, " +
                        "right = ${if (right[k] == 0) "nil" else right[k]}]\n"
            )
        }
        return s.toString()
    }

    init {
        this.parent = IntArray(size)
        this.key = Array(size) { null }
        this.left = IntArray(size)
        this.right = IntArray(size)
        this.pile = Pile(size)
        for (i in 1 until size) {
            this.freeObject(i)
        }

        for (i in 0 until size) {
            this.parent[i] = this.NIL
            this.right[i] = this.NIL
            this.left[i] = this.NIL
        }
    }
}
```
### 2.3. Classe _Binary Search Tree_

Esta classe representa uma árvore de pesquisa binária comum. Cada nó possuí uma chave e apontadores para os noś filhos e progenitor, como dito anteriormente. A sua propriedade principal é que para qualquer nó, se esse nó tiver um filho à esquerda, então a chave desse filho é inferior à sua chave; por outro lado,  se esse nó tiver um filho à direita, então a chave desse filho é superior à sua chave. Como o objetivo final é implementar uma árvore de pesquisa _red-black_, que por sua vez é um tipo particular de árvore de pesquisa binária, adicionou-se o modificador _open_ de modo a tornar esta classe herdável.

```kotlin
 **
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
```

### 2.4. Classe _Red Black Search Tree_

Como referido anteriormente as árvores *red-black* são uma variante das árvores de pesquisa binária. Ao restringir o modo como cada nó pode ser colorido no caminho entre a raiz da árvore e uma das folhas (propriedades referidas na introdução), assegura-se que nenhum desses caminhos contém mais do que o dobro de nós, do que outro qualquer, resultando numa árvore balanceada. 
Ao introduzir ou remover elementos, a árvore pode perder as suas propriedades. Para impedir que isso aconteção são necessários os métodos _righRotate_ e _leftRotate_ que são utilizados no método _insertFixUp_ que assegura essas propriedades após cada inserção.
```kotlin
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
            root == this.parent[this.left[root]] -> this.parent[this.left[root]] = y
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
            root == this.parent[this.right[root]] -> this.parent[this.right[root]] = y
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
```

## 3. Resultados Experimentais

Para testar o funcionamento do código desenvolvido, desenvolveu-se o seguinte script, que pega num ficheiro de texto, extrai todas as palavras e insere-as na árvore. Imprimindo da árvore, é possível verificar para cada palavra a posição da memória se encontra, a sua cor, e os nós progenitor e filhos. 
```kotlin

fun main() {
    val filename = "src/parte2/loremIpsum.txt"
    val file = File(filename)
    val text = file.readText(Charsets.UTF_8)
    var textArray = text.replace("[\\p{P}]".toRegex(), " ").split("\\s+".toRegex())
    textArray = textArray.distinct()
    val removeAll = textArray.toMutableList()
    removeAll.removeAll(listOf(""))
    textArray = removeAll.toList()
    val redBlackBTS = RedBlackSearchTree(textArray.size)
    for (word in textArray) redBlackBTS.insert(word)
    print(redBlackBTS)
}
```

Um exemplo poderá ser visionado [aqui](../pdfs/RBtreeOutput.pdf "Gráfico da Percentagem de pixeis por nível de cinzento.").
