import kotlin.math.floor

class FuelMass1 {

    fun calculateMass(list: MutableList<Int>): Int {
        var result = 0

        list.forEach {
            result += calculate(it)
        }

        return result
    }

    private fun calculate(value: Int): Int {
        val result = (floor(value.toDouble() / 3) - 2).toInt()
        return if (result > 0) result else 0
    }
}