import java.io.File

const val SEPARATOR = ","
const val FILE_NAME = "Day2/resource/input.txt"

fun main(args: Array<String>){

    val array = mutableListOf<Int>()

    File(FILE_NAME).forEachLine {
        it.split(SEPARATOR).forEach {
            array.add(it.toInt())
        }
    }

    val intCode = IntCode2(array)
    println(intCode.run(19690720))
}