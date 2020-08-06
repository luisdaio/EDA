<!--
    Relizado por: Luís Fernandes
    N.º: 17186
    E-mail : 17186@stu.ipbeja.pt
-->

# Complexidade Computacional e Algoritmos de Ordenação.
## 1. Introdução.
Nesta secção do projeto pretende-se estudar a complexidade computacional de algoritmos de ordenção, especificamente o _Bucket Sort_. Este algoritmo consiste em distribuir os elementos a ordenar por conjuntos mais pequenos, designados por _buckets_, ordenar cada _bucket_ individualmente utilizando outro algortimo de ordenação, e por fim reunir todos os _buckets_ de modo a obter o conjunto inicial ordenado. Este algoritmo torna-se útil quando o conjunto de números a ordenar se encontra uniformemente distribuido. Ao dividir o conjunto inicial em conjuntos mais pequenos, reduz-se o número total de comparações a serem feitas entre os elementos, reduzindo assim o tempo total. 

Posteriormente ao estudo, aplicou-se o algoritmo num programa, que utilizando a biblioteca de visão computacional __OpenCV__, recebe uma imagem __Full HD__ (1920 por 1080 píxeis) e devolve uma contagem ordenada do número de píxeis presente em cada nível de cinzento. 

## 2. Realização Experimental.
Para o estudo da complexidade computacional deste algoritmo, programou-se o mesmo com recurso à linguagem de programação _kotlin_. Programou-se também o algoritmo de ordenação escolhido para ordenar individualmente cada _bucket_, _Insertion Sort_. A realização experimental consiste em medir os tempos de médios execução, na ordenação de conjuntos (_arrays_) de números inteiros positivos aleatórios. A quantidade de números em cada conjunto está entre 10000 (dez mil) e 100000 (cem mil). Para cada quantidade foram feitas 500 (quinhentas) medições, de modo a obter o tempo médio com erro relativo inferior a 1%. 

### 2. 1. Código Desenvolvido.

#### 2. 1. 1. Classe _Insertion Sort_.
A classe abaixo representa a programação do algoritmo escolhido, _Inserion Sort_. Optou-se por tornar os métodos _sort()_ estáticos,
visto que são so únicos membros da classe, evitando assim a necessidade de instanciar a mesma.

```kotlin
/**
 * Class Insertion Sort
 */
class InsertionSort {
    companion object {
        /**
         * Sorts the input ArrayList.
         * @param array input Arraylist to sort
         * @return sorted Arraylist
         */
        @JvmStatic
        fun sort(array: ArrayList<Int>): ArrayList<Int> {
            // If the the array is empty or it's size is smaller then 2 then
            // it's already "sorted".
            if (array.isEmpty() || array.size < 2) {
                return array
            }
            for (count in 1 until array.count()) {
                val item = array[count] // Select the first unsorted element
                var i = count

                // as long as there is a smaller element than the selected on
                // it's left move that element to the right
                while (i > 0 && item < array[i - 1]) {
                    array[i] = array[i - 1]
                    i -= 1
                }
                array[i] = item // put the selected element int the right place
            }
            return array
        }

        /**
         * Sorts the input ArrayList of PixelsPerLevel, based on the number of .
         * @param array input Arraylist to sort
         * @return sorted Arraylist
         */
        @JvmStatic
        fun sort(array: ArrayList<PixelsPerLevel>){
            // If the the array is empty or it's size is smaller then 2 then
            // it's already "sorted".
            if (array.isEmpty() || array.size < 2) {
                return
            }
            for (count in 1 until array.count()) {
                val item = array[count] // Select the first unsorted element
                val nPixels = item.nPixels
                var i = count

                // as long as there is an element with a smaller number of pixels
                // than the selected on it's left move that element to the right
                while (i > 0 && nPixels < array[i - 1].nPixels) {
                    array[i] = array[i - 1]
                    i -= 1
                }
                array[i] = item // put the selected element int the right place
            }
        }
    }
}
```
#### 2. 1. 2. Classe _Bucket Sort_.

Seguidamente apresenta-se a classe _Bucket Sort_ que resulta de uma implementação do algoritmo com o mesmo nome. Como anteriormente, optou-se por tornar os métodos _sort()_ estáticos de modo a evitar a instânciar a classe. Para representar cada _bucket_ utilizaram-se listas de modo a poupar espaço.

```kotlin
/**
 * Class Bucket Sort
 * Implementation of Bucket Sort algorithm using Insertion Sort to sort each bucket
 */
class BucketSort {
    companion object {
        /**
         * Divides the input array in smaller lists (buckets), sorts each
         * bucket using Insertion Sort and
         * reassembles the buckets in the original array.
         * @param array input array to sort
         */
        @JvmStatic
        fun sort(array: IntArray) {
            val size = array.size

            //if the size is 0 or 1 the array is already "sorted"
            if (size < 2)
                return

            val nBuckets = sqrt(size.toDouble()).toInt()
            val buckets = Array(nBuckets + 1) { ArrayList<Int>() }
            val max = array.max()!!.toLong()
            val divider = ceil(((max + 1) / nBuckets).toDouble())

            // place each element in the respective bucket
            for (number in array) {
                val d = floor(number / divider).toInt()
                buckets[d].add(number)
            }

            // sort each bucket individually
            for (bucket in buckets) {
                InsertionSort.sort(bucket)
            }
            var index = 0

            // reassemble the buckets in the original array
            for (i in 0 until nBuckets) {
                for (j in 0 until buckets[i].size) {
                    array[index++] = buckets[i][j];
                }
            }
        }

        /**
         * Divides the input array in smaller lists (buckets), sorts each
         * bucket using Insertion Sort and
         * reassembles the buckets in the original array. This version is used
         * to sort the pairs (PixelsPerLevel)
         * based on the number of pixels.
         * @param array input array to sort
         */
        @JvmStatic
        fun sort(array: Array<PixelsPerLevel>, nBuckets: Int) {
            val size = array.size

            //if the size is 0 or 1 the array is already "sorted"
            if (size < 2)
                return

            val buckets = Array(nBuckets + 1) { ArrayList<PixelsPerLevel>() }
            val max = max(array)
            val divider = ceil(((max + 1) / nBuckets).toDouble())

            // place each element in the respective bucket
            for (pair in array) {
                val d = floor(pair.nPixels / divider).toInt()
                buckets[d].add(pair)
            }

            // sort each bucket individually
            for (bucket in buckets) {
                InsertionSort.sort(bucket)
            }
            var index = 0

            // reassemble the buckets in the original array
            for (i in 0 until nBuckets) {
                for (j in 0 until buckets[i].size) {
                    array[index++] = buckets[i][j]
                }
            }
        }

        /**
         * Returns the max number of pixels in the PixelsPerLevel array.
         * @param array Array of PixelsPerLevel
         * @return max number of pixels
         */
        private fun max(array: Array<PixelsPerLevel>): Int {
            var max = Int.MIN_VALUE
            for (pair in array) {
                if (max < pair.nPixels)
                    max = pair.nPixels
            }
            return max
        }
    }
}
```
A segunda função _sort()_ serve para ordenar a quantidade de pixeis nos niveis de cinzento numa imagem monocromática, através de pares que guardam a quantidade de pixeis e o respetivo nível. 

#### 2. 1. 3. Classe _Bucket Sort Tester_.

Para testar os tempos de execução foi desenvolvida a classe _BucketSortTester_ que contem algumas funções que ajudam a automatizar os processos de medição de tempos de execução, cálculo de médias, desvio padrão e erros relativos.

```kotlin
/**
 * Class BucketSortTester.
 * Tests the computational complexity of the sorting algorithm Bucket Sort.
 */
class BucketSortTester() {

    /**
     * Returns an array with random positive integers.
     * @param size size of the array
     */
    private fun arrayWithRandomInts(size: Int): IntArray {
        val random = Random
        return IntArray(size) { random.nextInt(Int.MAX_VALUE) }
    }

    /**
     * Returns the average bucket sort execution time with less than 1% error
     * (depending on the number of samples).
     * @param nElements number of elements to sort
     * @param nSamples number of times to sort the array and compute the mean
     * and standard deviation
     * @return the average execution time
     */
    fun meanTimeWithError(nElements: Int, nSamples: Int): Double {
        val times = DoubleArray(nSamples)
        for (i in 0 until nSamples) {
            times[i] = sortingTime(nElements)
        }
        val meanAndStDeviation = getMeanAndStDeviation(times)
        val relativeError = getRelativeError(meanAndStDeviation)
        println("Relative error: $relativeError ")
        return meanAndStDeviation.first
    }

    /**
     * Returns a Pair that contains the mean and standard deviation of the
     * input array
     * @param times input array
     * @return A pair containing the mean and standard deviation
     */
    private fun getMeanAndStDeviation(times: DoubleArray): Pair<Double, Double> {
        var standardDeviation = 0.0
        val mean = getMean(times)
        for (time in times) {
            standardDeviation += (time - mean).pow(2)
        }
        standardDeviation = sqrt(standardDeviation / times.size)
        return Pair(mean, standardDeviation)
    }

    /**
     * Returns the mean value for.
     * @param array array containing the values
     * @return
     */
    private fun getMean(array: DoubleArray): Double {
        val mean = array.sum()
        return mean / array.size
    }

    /**
     * Returns the percentage relative error.
     * @param meanAndStDeviation pair that contains the mean and standard
     * deviation
     * @return percentage relative error
     */
    private fun getRelativeError(meanAndStDeviation: Pair<Double, Double>): Double {
        return (meanAndStDeviation.second / meanAndStDeviation.first) * 100.0
    }

    /**
     * Returns the bucket sort execution time for an array with random
     * integers, in milliseconds.
     * @param nElements number of elements to sort
     * @return execution time
     */
    fun sortingTime(nElements: Int): Double {
        val arrayWithRandomInts = arrayWithRandomInts(nElements)
        return measureTimeMillis { BucketSort.sort(arrayWithRandomInts) }.toDouble()
    }
}
``` 
#### 2. 1. 4. Script _Main_.

Para utlizar a _BucketSortTester_ foi desenvolvido o script "Main.kt". Na função _main()_ começa-se por inicializar algumas variáveis necessárias para o funcionamento do programa. O intervalo escolhido para o teste dos tempos de execução do algoritmo foi [10000:100000], de modo a obter tempos relativamente grandes. Para possibilitar a comparação dos resultados obtidos
com os resultados esperados (complexidade $O(n²)$ para os piores casos) é necessário proceder à normalização dos dados, multiplicando os mesmos pelos pelo respetivo factor $k$, em que $k = \frac{1}{(max-min)} \times 100$, sendo que $max$ e $min$ são os valores máximo e mínimo, respetivamente, no intervalo utilizado. Posteriormente procede-se ao cálculo dos tempos de execução e respetivo tempo esperado, que são escritos no ficheiro "_bucketSortTimes_". Os dados são depois utilizados na elaboração de gráficos com recurso à ferramenta __Gnuplot__.
```kotlin
/**
 * Driver function
 */
fun main() {
    /**
     * This part of the program corresponds to the test of the Bucket Sort
     * algorithm execution time.
     * To run it, uncomment lines 13 to 26, comment everything else and run
     * the main function.
     */
    val bucketSortTimes = "src/parte1/bucketSortTimes.txt"
    val file = File(bucketSortTimes)
    val tester = BucketSortTester()
    val start = 10000
    val end = 100000
    val ke = abs(1.0 / (tester.sortingTime(end) - tester.sortingTime(start))) * 100.0
    val kt = abs(1.0 /((end.toLong() * end.toLong()) - (start.toLong() * start.toLong()))) * 100.0

    file.printWriter().use { out ->
        for (i in start..end step 1000) {
            out.println("$i ${tester.meanTimeWithError(i, 500) * ke} ${((i.toLong() * i.toLong()) * kt)}")
        }
    }
}
```

É possível observar os tempos de execução esperados neste [gráfico](../pdfs/tempoEsperado.pdf "Gráfico dos tempos esperados"), e uma comparação entre os tempos esperados e os tempos obtidos neste [gráfico](../pdfs/graficoComparativoBucket.pdf "Gráfico Comparativo dos tempos de execução").


### 2. 2. Ordenação da Quantidade de Píxeis Presentes numa Imagem Full HD em cada Nível de Cinzento

#### 2. 2. 1. Classe _Pixels Per Level_
A classe abaixo foi desenvolvida com o único propósito de guardar a quantidade de pixeis e o respectivo nível de cinzento, para ser utilizada na contagem de píxeis. 
```kotlin
/**
 * Class PixelsPerLevel.
 * Represents a pair whose first value corresponds to the level of gray and 
 * the second to the number of pixels present
 * in that level, of an image.
 */
class PixelsPerLevel(level: Int, nPixels: Int) {
    var level: Int = level
    var nPixels: Int = nPixels
}
```
#### 2. 2. 2. _Main_

Na segunda parte de função _main_ está presente o precedimento para a contagem de pixeis em cada nível de cinzento. Para fazer a leitura da imagem foi utilizada a biblioteca de visão computacional __OpenCV__. A utilização paramêtro _Imgcodecs.IMREAD_GRAYSCALE_ permite converter uma imagem a cores para uma monocromática com tons de cinzento.

```kotlin
/**
 * Driver function
 */
fun main() {

    /**
     * This part of the program corresponds to sorting the count of the number
     * of pixels for each gray level of an image.
     * To run it, uncomment lines 32 to 52, comment everything else and run 
     * the main function.
     */
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    val imagePath = "/home/img.jpg"
    val filePath = "src/parte1/orderedPixelCount.txt"
    var orderedPixelCountFile = File(filePath)
    val imgMat = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_GRAYSCALE)
    val pixelsPerLevel = Array(256){ i -> PixelsPerLevel(i,0) }
    val nPixels = imgMat.cols() * imgMat.rows()

    // count the number of pixels in each level
    for (row in 0 until imgMat.rows()) {
        for (col in 0 until imgMat.cols()) {
            pixelsPerLevel[imgMat.get(row, col)[0].roundToInt()].nPixels++
        }
    }

    // sort the pixelPerLevel pair based on the number of pixels, usind 100 buckets
    BucketSort.sort(pixelsPerLevel, 100)

    // write the data to a file which the first, second and third columns are
    // the level, the number of pixels on that level and the percentage, 
    // respectively
    orderedPixelCountFile.printWriter().use { out ->
        pixelsPerLevel.forEach {p ->
            var percentage: Double = (p.nPixels * 100.0) / nPixels
            out.println("${p.level} ${p.nPixels} $percentage")
        }
    }
}
```
O gráfico realizado com os dados resultantes pode visualizado [aqui](../pdfs/pixeisPorNivel.pdf "Gráfico da Percentagem de pixeis por nível de cinzento.").