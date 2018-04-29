fun main(args: Array<String>) {
    val data: IntArray = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    val route = readLine()!!.toCharArray()
    print(if (route[--data[1]] == route[--data[2]]) 0 else 1)
}
