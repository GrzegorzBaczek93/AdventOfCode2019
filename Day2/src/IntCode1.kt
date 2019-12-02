
class IntCode1(private val array: MutableList<Int>) {

    private var currentPosition = 0

    fun run(): Int {
        while (array[currentPosition] != Operation.Halt.value) {
            calculate()
            stepForward()
        }

        return array[0]
    }

    fun restore() {
        array[1] = 12
        array[2] = 2
    }

    private fun calculate() {
        val first = array[currentPosition + 1]
        val second = array[currentPosition + 2]
        val res = array[currentPosition + 3]

        array[res] = Operation.calculate(array[currentPosition], array[first], array[second])
    }

    private fun stepForward() {
        currentPosition += Operation.getStep(array[currentPosition])
    }

    private fun displayArray() {
        array.forEach {
            print("$it,")
        }
        println()
    }

    private enum class Operation(val value: Int, val step: Int, val func: (v1: Int, v2: Int) -> Int ) {
        Addition(1, 4, { v1, v2 -> v1 + v2 }),
        Multiplication(2, 4, { v1, v2 -> v1 * v2}),
        Halt(99, 1, { _, _ -> 0 });

        companion object {
            fun getStep(value: Int): Int {
                return Operation.values().first { it.value == value }.step
            }

            fun calculate(value: Int, v1: Int, v2: Int): Int {
                return Operation.values().first { it.value == value }.func(v1, v2)
            }
        }
    }
}