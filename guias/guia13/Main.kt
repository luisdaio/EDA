package guia13

import org.neuroph.core.data.DataSet
import org.neuroph.core.data.DataSetRow
import org.neuroph.nnet.Perceptron

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 16/06/20
 */

/**
 * Neuroph Library Example
 */
fun main() {
    val neuralNetwork = Perceptron(2, 1)
    val trainingSet = DataSet(2, 1)
    trainingSet.add(DataSetRow(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0)))
    trainingSet.add(DataSetRow(doubleArrayOf(0.0, 1.0), doubleArrayOf(1.0)))
    trainingSet.add(DataSetRow(doubleArrayOf(1.0, 0.0), doubleArrayOf(1.0)))
    trainingSet.add(DataSetRow(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0)))

    neuralNetwork.learn(trainingSet)
    val filename = "/src/guia13/or_perceptron.nnet"
    neuralNetwork.save(filename)

    neuralNetwork.setInput(1.0,1.0)
    neuralNetwork.calculate()
    var output = neuralNetwork.output
}
