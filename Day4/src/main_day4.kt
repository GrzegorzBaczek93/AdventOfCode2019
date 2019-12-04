import java.io.File

const val FILE_NAME = "Day4/resource/input.txt"

fun main(args: Array<String>){

    val array = mutableListOf<String>()

    File(FILE_NAME).forEachLine {
        array.add(it)
    }

    val secureContainer = SecureContainer2()
    secureContainer.run(array)

    println(array)
}