import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val array: IntArray = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    val min = min(array[0], array[1])

    if (min + array[2] <= max(array[0], array[1])) println((min + array[2]).times(2))
    else println(max(array[0], array[1]).plus(array[2] - abs(array[0] - array[1]) shr 1).times(2))
}
