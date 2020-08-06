<!--
    Relizado por: Luís Fernandes
    N.º: 17186
    E-mail : 17186@stu.ipbeja.pt
-->

# Grafos
## 1. Introdução

Nesta secção do trabalho pretende-se fazer o estudo da estrutura de dados designada por grafo. Esta estrutura de dados é uma representação de um conjunto de objectos sendo que, alguns pares desses objectos de encontram ligados. Estes objectos são dignados por nós (ou vértices) e as ligaçãos entre eles são designadas por ramos. 

Esta secção tem também como objectivo o estudo do algoritmo de Bellman-Ford, que resolve o problema dos caminhos mais curtos a partir de um único nó. 

## 2. Realização Experimental
Para o estudo desta estrutura de dados, programou-se a mesma com recurso a listas de adjacência. Cada nó possui uma lista de nós adjacentes. Além disso, o grafo possui uma lista de ramos, incluindo os pesos associados ao mesmo.

Programou-se também o algoritmo de Bellman-Ford. Este algoritmo resolve o problema dos caminhos mais curtos em grafos orientados e ponderados, neste caso, do tipo origem única. Este algoritmo procede à relaxação sucessiva dos ramos, diminuindo uma estimativa do peso do caminho desde a oridem até cada um dos vértices, até atingir o caminho mais curto para cada um.

Para testar o funcionamento do algoritmo foi desenvolvido um script que cria uma tabela bidimensional com distâncias aleatórias e utiliza o algoritmo para calcular o caminho mais rápido entre o nó origem e todos os outros nós. 


## 3. Código Desenvolvido

Todo o código desenvolvido neste trabalho é baseado no código disponibilizado pelo docente nos guias das aulas laboratoriais.

### 3.1. Classe _Vertex_.

A classe abaixo representa o nó (ou vértice) de um grafo. 

```kotlin
/**
 * Class Vertex.
 * Represents a vertex (or node) of a graph.
 * @property key vertex key
 */
class Vertex<T>(val key: T) : Comparable<Vertex<T>> {
    var adj = arrayListOf<Pair<Int, Vertex<T>>>()
    var d: Int? = null
    var pi: Vertex<T>? = null

    override fun toString(): String {
        val s = StringBuilder()
        s.append(this.key)
        var sAdj = " "
        for (adj in this.adj) {
            sAdj += "${adj.second.key} "
        }
        s.append(sAdj)
        return s.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (super.equals(other)) return true
        else if (other !is Vertex<*>) return false
        return (other.key == this.key && other.d == this.d &&
                other.pi == this.pi && other.adj == this.adj)
    }

    override fun compareTo(other: Vertex<T>) = compareValuesBy(this, other, { it.d }, { it.pi })
}
```

### 3.2. Classe _Edge_.

A classe abaixo representa o ramo (ou aresta) de um grafo. Dado que é usada na representação de um grafo orientado, umas das propriedades é o peso, para além dos dois vértices que compõem a ligação.

```kotlin
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
```
### 3.3. Classe _Graph_.
A classe abaixo representa a estrutura de dados designada por grafo, ponderado e orientado (neste caso). 
```kotlin
/**
 * Class Graph.
 * Represents a graph.
 * @property vertexes List of vertexes (or nodes).
 * @property edges List of edges
 */
open class Graph<T : Comparable<T>>(val vertexes: ArrayList<Vertex<T>>, val edges: ArrayList<Edge<T>>) {

    /**
     * Creates the edges between the nodes.
     */
    private fun createEdges() {
        for (edge in this.edges) {
            val w = edge.weight
            val v1 = edge.vertex0
            val v2 = edge.vertex1
            v1.adj.add(Pair(w, v2))
        }
    }

    override fun toString(): String {
        var s = ""
        for (v in vertexes) s = s + "v = " + v.key + " pi = " + v.pi + "\n"
        return s
    }

    init {
        this.createEdges()
    }
}
```

### 3.4. Classe _BellmanFord_.

A classe abaixo implementa o algoritmo de Bellman-Ford, extendendo a classe _Graph_. Para além das funções _run()_ e _relax()_, possuí um construtor secundário que recebe uma tabela bidimensional com os valores das distâncias e cria o grafo a o partir da mesma. Os indíces da tabela representam os vértices do grafo. Caso exista um ramo que ligue dois vértices, o valor correspondente à interseção desses indices na tabela é diferente de zero. Por exemplo, se na posição (1,2) da tabela estiver o valor 10, significa que existe um ramo que parte do vértice 1 e acaba no vértice 2 com peso 10.

```kotlin
**
 * Class BellmanFord.
 * Extends the Graph class and implements the Bellman-Ford algorithm to find the shortest path between a node and allliga
        Graph<T>(vertexes, edges) {
    private val d: MutableMap<Vertex<T>, Int> = mutableMapOf()
    private val pi: MutableMap<Vertex<T>, Vertex<T>?> = mutableMapOf()

    /**
     * Decrease, if possible, the distance of a node.
     * @param u path node
     * @param v destination node
     * @param w weight
     */
    private fun relax(u: Vertex<T>, v: Vertex<T>, w: Int) {
        if (d[v]!! > d[u]!! + w) {
            d[v] = d[u]!! + w
            pi[v] = u
        }
    }

    /**
     * Finds the shortest path between a node and all the other nodes in the graph.
     * @param s starting node (or vertex).
     * @return true if the the path contains all the nodes, false otherwise
     */
    fun run(s: Vertex<T>): Boolean {
        d[s] = 0
        for (i in 1 until this.vertexes.size) {
            for (edge in this.edges) {
                val u = edge.vertex0
                val v = edge.vertex1
                val w = edge.weight
                relax(u, v, w)
            }
        }

        for (edge in this.edges) {
            val u = edge.vertex0
            val v = edge.vertex1
            val w = edge.weight
            if (d[v]!! > d[u]!! + w) return false
        }
        return true
    }

    /**
     * Finds the shortest path between a node and all the other nodes in the graph, starting at the first node added to
     * the graph.
     */
    fun run() {
        this.run(this.vertexes[0])
    }

    override fun toString(): String {
        val s: StringBuilder = StringBuilder()liga
        }
    }

    /**
     * Secondary constructor.
     * Creates the a graph given it's distance matrix.
     * @param distanceMatrix the graph distance matrix
     */
    constructor(distanceMatrix: Array<IntArray>) : this(ArrayList(distanceMatrix.size), arrayListOf()) {
        var distance: Int
        vertexes.addAll(distanceMatrix.indices.map { Vertex(it as T) })
        for (i in distanceMatrix.indices) {
            for (j in distanceMatrix[i].indices) {
                if (i < j) {
                    distance = distanceMatrix[i][j]
                    if (distance != 0) {
                        edges.add(Edge(distance, vertexes[i], vertexes[j]))
                    }
                }
            }
        }
        for (vertex in this.vertexes) {
            d[vertex] = Int.MAX_VALUE
            pi[vertex] = null
        }
    }
}
```

## 3.5. Script _Main_.

O script seguinte testa o funcionamento do algoritmo Bellman-Ford. Cria-se uma tabela bidimensional com distâncias aleatórias (entre 0 e 100). Foi pedido que se escolhesse o tamanho para a tabela de modo que o tempo de execução fosse aproximadamente 600 segundos (10 minutos) e neste caso trata-se de 4000.

```kotlin
fun main(args: Array<String>) {
    val N = if (args.isEmpty()) 4000 else args[0].toInt()

    // distance matrix with random numbers
    val distanceMatrix = Array(N) { IntArray(N) { 0 } }
    for (i in distanceMatrix.indices) {
        for (j in distanceMatrix[i].indices) {

            // fill only the upper triangle of the matrix
            if (i < j) {
                distanceMatrix[i][j] = (0..100).random()
            }
        }
    }

    val graph: BellmanFord<Int> = BellmanFord(distanceMatrix)
    println("ANTES:")
    println(graph)
    println("-------------------------------------------------\n\nDEPOIS:")
    val time = measureTimeMillis { graph.run() }
    println(graph)
    println("Elapsed time = ${TimeUnit.MILLISECONDS.toMinutes(time)} minutes.")
}
```