import java.io.File

const val FILE_NAME = "Day1/resource/input.txt"

fun main(args: Array<String>){

    val array = mutableListOf<Int>()

    File(FILE_NAME).forEachLine {
        array.add(it.toInt())
    }

    val fuelMass = FuelMass2()
    println(fuelMass.calculateMass(array))
}