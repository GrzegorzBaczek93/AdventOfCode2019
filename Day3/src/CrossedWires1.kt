import kotlin.math.abs

class CrossedWires1 {

    var currentPair = Pair(0, 0)

    fun run(array: MutableList<String>): Int {
        val firstVectorInstructions = parse(array[0])
        val secondVectorInstructions = parse(array[1])

        val firstVector = HashSet<Pair<Int, Int>>()
        val secondVector = HashSet<Pair<Int, Int>>()

        executeAddInstructions(firstVectorInstructions, firstVector)
        executeVerifyInstructions(secondVectorInstructions, firstVector, secondVector)

        return calculateLowestManhattanDistance(secondVector)
    }

    private fun calculateLowestManhattanDistance(vector: HashSet<Pair<Int, Int>>): Int {
        var lowest = 0
        vector.forEach {
            val distance = abs(it.first) + abs(it.second)

            if (lowest == 0) {
                lowest = distance
            } else {
                if (distance < lowest) {
                    lowest = distance
                }
            }
        }
        return lowest
    }

    private fun executeAddInstructions(firstVectorInstructions: MutableList<Pair<Char, Int>>, vector: HashSet<Pair<Int, Int>>) {
        firstVectorInstructions.forEach {
            for (counter in 1..it.second) {
                vector.add(createPair(it))
            }
        }
        createPair(null)
    }

    private fun executeVerifyInstructions(secondVectorInstructions: MutableList<Pair<Char, Int>>, firstVector: HashSet<Pair<Int, Int>>, secondVector: HashSet<Pair<Int, Int>>) {
        secondVectorInstructions.forEach {
            for (counter in 1..it.second) {
                val pair = createPair(it)
                if (firstVector.contains(pair)) {
                    secondVector.add(pair)
                }
            }
        }
        createPair(null)
    }

    private fun createPair(instruction: Pair<Char, Int>?): Pair<Int, Int> {
        val pair = when (instruction?.first) {
            'U' -> Pair(currentPair.first, currentPair.second + 1)
            'D' -> Pair(currentPair.first, currentPair.second - 1)
            'R' -> Pair(currentPair.first + 1, currentPair.second)
            'L' -> Pair(currentPair.first - 1, currentPair.second)
            else -> Pair(0, 0)
        }
        currentPair = pair
        return pair
    }

    private fun parse(array: String): MutableList<Pair<Char, Int>> {
        val parsed = mutableListOf<Pair<Char, Int>>()
        val delimiter = ','

        array.split(delimiter).forEach {
            val instruction = it.first()
            val value = it.substring(1, it.length).toInt()

            parsed.add(Pair(instruction, value))
        }

        return parsed
    }
}