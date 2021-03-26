import java.io.File
import kotlinx.coroutines.*

fun main() {
    val dictionary = HashSet<String>()
    val minimumLength = 10
    File("/Users/olesyamartinyuk/Downloads/GameWords/src/dictionary.txt")
        .inputStream()
        .bufferedReader()
        .useLines {
            it.forEach { word ->
                dictionary.add(word)
            }
        }
    val randomWord = dictionary
        .filter { it.length > minimumLength }
        .random()

    var myWord = ""
    val endWord = "0"
    println("Enter words that has letters from word '$randomWord'. Enter 0 to stop entering")
    val userWords = HashSet<String>()
    while (myWord != endWord) {
        myWord = (readLine()!!).toLowerCase().trim()
        if (myWord != endWord) {
            userWords.add(myWord)
        }
    }
    println("Saving entered words into file")

    runBlocking {
        val fileJob = CoroutineScope(Dispatchers.IO).launch {
            File("/Users/olesyamartinyuk/Downloads/GameWords/src/my_words.txt").printWriter().use { out ->
                userWords.forEach { word ->
                    out.write(word + "\n")
                }
            }
        }
        async {
            var score = 0
            val chars = randomWord.toSet()
            userWords.forEach {
                if (!chars.containsAll(it.toSet())) {
                    println("Word '$it' contains extra characters")
                    return@forEach
                }
                if (!dictionary.contains(it)) {
                    println("Could not find word matching '$it' in a dictionary")
                    return@forEach
                }
                score += it.length
            }
            println("Your score is $score")
        }
        fileJob.join()
    }
}