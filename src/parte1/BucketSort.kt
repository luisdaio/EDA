package parte1

import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 */

/**
 * Class Bucket Sort
 * Implementation of Bucket Sort algorithm using Insertion Sort to sort each bucket
 */
class BucketSort {
    companion object {
        /**
         * Divides the input array in smaller lists (buckets), sorts each bucket using Insertion Sort and
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
         * Divides the input array in smaller lists (buckets), sorts each bucket using Insertion Sort and
         * reassembles the buckets in the original array. This version is used to sort the pairs (PixelsPerLevel)
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
