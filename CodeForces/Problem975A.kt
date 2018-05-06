import java.util.StringTokenizer
import java.util.LinkedList
import kotlin.collections.HashMap

fun main(args: Array<String>) {

    readLine()
    val token = StringTokenizer(readLine())
    val mapList = LinkedList<HashMap<Char, Boolean>>()

    while (token.hasMoreTokens()){
        val map = HashMap<Char, Boolean>()
        token.nextToken().forEach { map[it] = true }
        if (mapList.none { it == map }) mapList.addFirst(map)
    }
    
    println(mapList.size)
}
