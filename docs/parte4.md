<!--
    Relizado por: Luís Fernandes
    N.º: 17186
    E-mail : 17186@stu.ipbeja.pt
-->

# Concorrência e Paralelismo
## 1. Introdução

Nesta secção do trabalho pretende-se fazer o estudo de concorrẽncia e paralelismo suportados pela linguagem Kotlin. Para tal, foi utilizada a biblioteca de processamento de imagem OpenCV para realizar a leitura duma imagem a cores no formato 4K _Ultra HD_ (3840 × 2160 pxl) e calcular a média e desvio padrão de cada componente de cor, em cada bloco de dimẽnsão 192 × 108 pxl, pertencente à imagem, com recurso a _threads_. 


## 2. Código Desenvolvido

Nesta secção apresenta-se o código desenvolvido.

### 2.1. Classe _ImageStats_.

A classe abaixo é reponsável por realizar o cálculo  da média e do desvio padrão dos componentes de cor da imagem. As suas propriedades permitem a utlização da mesma em blocos de tamanho variável ou uma imagem por completo. O método _calculate_ devolve um array contendo os valores da média e desvio padrão para cada componente de cor.
```kotlin
/**
 * Class ImageStats.
 * Calculates the mean and standard deviation of each color channel, given the image matrix.
 * @property startX starting x coordinate
 * @property endX ending x coordinate
 * @property startY starting y coordinate
 * @property endY starting y coordinate
 * @property imageMat image matrix
 */
open class ImageStats(private val startX: Int, private val endX: Int, private val startY: Int, private val endY: Int,
                      private val imageMat: Mat) {
    /**
     * Calculates the mean and standard deviation for each color channel of the image
     * @return array containing the mean and standard deviation for each color channel
     */
    fun calculate(): Array<Double>? {
        val size = 192 * 108
        val b = DoubleArray(size)
        val g = DoubleArray(size)
        val r = DoubleArray(size)
        var it = 0

        // adding the value of each color channel to the respective array
        for (x in startX until endX) {
            for (y in startY until endY) {
                try {
                    b[it] = imageMat.get(y, x)[0]
                    g[it] = imageMat.get(y, x)[1]
                    r[it] = imageMat.get(y, x)[2]
                    it++

                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
            }
        }

        // calculating the mean of each color channel
        val bMean = this.mean(b)
        val gMean = this.mean(g)
        val rMean = this.mean(r)

        // returnig the mean and standard deviation of each color channel
        return arrayOf(bMean, stdDeviation(b, bMean), gMean, stdDeviation(g, gMean), rMean, stdDeviation(r, rMean))
    }

    /**
     * Calculates the mean of an array of Doubles
     * @param array array containing the values
     * @return mean
     */
    private fun mean(array: DoubleArray): Double {
        return (array.sum() / array.size)
    }

    /**
     * Calculates the standard deviation of an array of Doubles
     * @param array array containing the values
     * @param mean mean value of the doubles in the array
     * @return standard deviation
     */
    private fun stdDeviation(array: DoubleArray, mean: Double): Double {
        val d: List<Double> = array.map { x -> (x - mean).pow(2.0) }
        return (sqrt(d.sum() / (array.size)))
    }
}
```

### 2.2. Classe _ImageStatsCallable_.

Esta classe foi densenvolvida com o intuíto de permitir a utilização da classe _ImageStats_ em _threads_ diferentes, por isso implementa a interface _Callable_. Esta interface é semelhante à _Runnable_ no sentido em que ambas foram desenhadas para serem implementadas por classes cujas instâncias podem ser utilizadas por outras _threads_. No entanto, a primeira permite  devolver um resultado, assim como levantar exceções.
```kotlin
/**
 * Class ImageStatsCallable.
 * Callable for the ImageStats class
 * @property startX starting x coordinate
 * @property endX ending x coordinate
 * @property startY starting y coordinate
 * @property endY ending y coordinate
 * @property imageMat image matrix
 */
class ImageStatsCallable(startX: Int, endX: Int, startY: Int, endY: Int,
                         imageMat: Mat) : ImageStats(startX, endX, startY, endY, imageMat),
        Callable<Array<Double>> {
    override fun call(): Array<Double>? {
        return super.calculate()
    }
}
```

### 2.3. Script _Main_.

Este script mostra o funcionamento da classe _ImageStats_ e _ImageStatsCallable_ para o cálculo da média e desvio padrão de cada bloco da imagem, com e sem _threads_, respectivamente. Para implementar o uso de _threads_ optou-se por utilizar o método estático _Executors.newFixedThreadPool_ que permite criar uma _pool_ de _threads_ que reutiliza um número predefinido de _threads_ para executar tarefas. Cada vez que é submetida uma tarefa, é criada uma nova _thread_ para a executar. Se o número máximo de _threads_ (o número predefinido) for atingido as tarefas são postas num lista de espera até que volte a haver uma _thread_ disponível na _pool_. Neste caso as tarefas a serem executadas são instâncias da classe _ImageStatsCallable_ utilizadas para fazer os cálculos em cada bloco. 

```kotlin
/**
 * Driver function.
 */
fun main(args: Array<String>) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    val imagePath = if (args.isEmpty()) "/home/img.jpg" else args[0]
    val statsFile = File("src/parte4/imageStats.txt")
    val imgMat = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_COLOR)
    var stats: Array<Array<Double>>? = null
    val nIterations = 100
    val times = Array(nIterations) { 0.0 }
    val timesWithThreads = Array(nIterations) { 0.0 }

    // calculate the execution times with and without threads
    for (i in 0 until nIterations) {
        times[i] = measureTimeMillis { stats = run(imgMat) }.toDouble()
        timesWithThreads[i] = measureTimeMillis {  runWithThreads(imgMat) }.toDouble()
    }

    // save the results in a file
    statsFile.printWriter().use { out ->
        for (i in stats!!.indices) {
            out.println("$i ${stats!![i][0]} ${stats!![i][1]} ${stats!![i][2]} ${stats!![i][3]} ${stats!![i][4]}" +
                    " ${stats!![i][5]}")
        }
    }

    // calculate the relative error of the execution times
    val relativeError = relativeError(times)
    val relativeErrorWithThreads = relativeError(timesWithThreads)

    // print the results
    println("Tempo médio : ${mean(times)} ns, Erro: ${String.format("%.1f", relativeError)}% \n" +
            "Tempo médio com threads: ${mean(timesWithThreads)} ns, Erro: ${String.format("%.1f", relativeErrorWithThreads)}%")
}

/**
 * Calculates the image stats (mean and standard deviation for each color channel) in blocks of 108x192 px
 * @param imgMat opencv's matrix that represents the image
 */
fun run(imgMat: Mat): Array<Array<Double>> {
    var block = 0
    val width = imgMat.rows() / 108
    val height = imgMat.cols() / 192
    val stats = Array(100) { Array(6) { 0.0 } }

    for (i in 0 until width) {
        for (j in 0 until height) {
           stats[block++] = ImageStats(i * 192, (i + 1) * 192, j * 108, (j + 1) * 108, imgMat).calculate()!!
        }
    }
    return stats
}

/**
 * Calculates the image stats (mean and standard deviation for each color channel),
 * in blocks of 108x192 px using an Executor
 * @param imgMat opencv's matrix that represents the image
 */
fun runWithThreads(imgMat: Mat) {

    val width = (imgMat.rows() / 108)
    val height = (imgMat.cols() / 192)
    val nThreads = Runtime.getRuntime().availableProcessors()
    val executor = Executors.newFixedThreadPool(nThreads)
    var startX: Int
    var endX: Int
    var startY: Int
    var endY: Int

    // for each block add a callable to the Executor
    for (j in 0 until width) {
        for (k in 0 until height) {
            startX = j * 192
            endX = (j + 1) * 192
            startY = k * 108
            endY = (k + 1) * 108
            executor.submit(ImageStatsCallable(startX, endX, startY, endY, imgMat))
        }
    }

    // wait until all threads are finished
    executor.shutdown()
}

/**
 * Calculates the mean of an array of Doubles
 * @param array array containing the values
 * @return mean
 */
private fun mean(array: Array<Double>): Double {
    return (array.sum() / array.size)
}

/**
 * Calculates the standard deviation of an array of Doubles
 * @param array array containing the values
 * @return standard deviation
 */
private fun stdDeviation(array: Array<Double>): Double {
    val mean = mean(array)
    val d: List<Double> = array.map { x -> (x - mean).pow(2.0) }
    return (sqrt(d.sum() / (array.size)))
}

/**
 * Calculates the relative error of an array in percentage.
 * @param array array containing the values
 * @return relative error
 */
private fun relativeError(array: Array<Double>): Double {
    val stdDeviation = stdDeviation(array)
    val mean = mean(array)
    return (stdDeviation / mean) * 100.0
}
```
## 3. Resultados Experimentais

Para testar o código desenvolvido foi utilizado um sistema com as seguintes características:

* Sistema Operativo: Windows 10 Home 
* Processador: Intel(R) Core(TM) i7-8750 CPU @ 2.20GHz (6 núcleos, 12 _threads_)
* Memória RAM: 16 GB
* Máquina Virtual : 
    * Sitema de Virtualização: VMware Workstation 15
    * Sistema Operativo: Ubuntu 18.04 LTS
    * Processador: 3 núcleos dos 6
    * Memória RAM: 8 GB dos 16

Ao executar o código com 100 iterações (medições do tempo de execução) obteve-se o seguinte [resultado](../pdfs/results.pdf "Resultado"). Com um tempo médio de execução de 675 nano segundos e 2 nano segundos com _threads_, deu-se uma redução de aproximadamente 99%. O erro do tempo médio com threads é bastante elevado devido à variação elevada de alguns tempos ao longo das medições.