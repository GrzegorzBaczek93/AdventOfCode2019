import java.io.File

const val FILE_NAME = "Day3/resource/input.txt"

fun main(args: Array<String>){

    val array = mutableListOf<String>()

    File(FILE_NAME).forEachLine {
        array.add(it)
    }

    val crossedWires = CrossedWires2()
    val result = crossedWires.run(array)
    println(result)
}