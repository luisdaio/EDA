package parte1

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 */

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
            // If the the array is empty or it's size is smaller then 2 then it's already "sorted".
            if (array.isEmpty() || array.size < 2) {
                return array
            }
            for (count in 1 until array.count()) {
                val item = array[count] // Select the first unsorted element
                var i = count

                // as long as there is a smaller element than the selected on it's left move that element to the right
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
        fun sort(array: ArrayList<PixelsPerLevel>){
            // If the the array is empty or it's size is smaller then 2 then it's already "sorted".
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