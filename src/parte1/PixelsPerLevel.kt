package parte1

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 */

/**
 * Class PixelsPerLevel.
 * Represents a pair whose first value corresponds to the level of gray and the second to the number of pixels present
 * in that level, of an image.
 */
class PixelsPerLevel(level: Int, nPixels: Int) {
    var level: Int = level
    var nPixels: Int = nPixels

    override fun toString(): String {
        return "(level=$level, nPixels=$nPixels)"
    }
}
