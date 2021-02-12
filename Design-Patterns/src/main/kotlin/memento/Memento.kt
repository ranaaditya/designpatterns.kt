package memento

/**
 * @author ranaaditya
 *
 * Memento Design Pattern
 *
 * This Design pattern has different components which make it:
 *
 * 1. Memento - stores internal state of the Originator object
 *
 * 2. Originator - creates a memento containing a snapshot of its current
 * internal state and uses Memento to restore its internal state
 *
 * 3. Caretaker - responsible for the memento's safekeeping, never
 * operates / examines the content of a Memento
 */

/**
 * Originator class component
 */
class FileWriterUtils(file: String) {
    private var fileName: String = file
    private var content: StringBuilder = StringBuilder()

    override fun toString(): String {
        // return super.toString()
        return this.content.toString()
    }

    fun write(str: String) {
        content.append(str)
    }

    fun save(): Memento {
        return Memento(this.fileName, this.content)
    }

    fun undoToLastSave(obj: Any) {
        val memento = obj as Memento
        this.fileName = memento.fileName
        this.content = memento.content
    }

    /**
     * Memento class component
     */
    inner class Memento(file: String, content: StringBuilder) {
        internal var fileName: String = file
        internal var content: StringBuilder = StringBuilder(content)
    }
}

/**
 * Caretaker class component
 */
class FileWriterCaretaker {
    private lateinit var obj: Any

    fun save(fileWriter: FileWriterUtils) {
        obj = fileWriter.save()
    }

    fun undo(fileWriter: FileWriterUtils) {
        fileWriter.undoToLastSave(obj)
    }
}

fun main() {
    val caretaker = FileWriterCaretaker()
    val fileWriter = FileWriterUtils("code.kt")

    fileWriter.write("fun main() {  println(\"Hello World \") }")
    println("FileWriter : $fileWriter")

    caretaker.save(fileWriter)

    fileWriter.write("fun hasCode(): Boolean = true")
    println("Filewriter : $fileWriter")

    caretaker.undo(fileWriter)
}

