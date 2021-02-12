package protectionproxy

import java.util.*

/**
 * @author ranaaditya
 *
 * Interface
 */
interface Batch {
    fun totalStudents(): Int
    fun registerStudent(name: String)
}

/**
 * Class to be proxyed.
 */
public open class CourseBatch : Batch {
    private val listOfStudents: MutableList<String>
    override fun totalStudents(): Int {
        return listOfStudents.size + 1
    }

    override fun registerStudent(name: String) {
        listOfStudents.add(name)
        println(" Student name : $name")
    }

    init {
        listOfStudents = ArrayList()
    }
}

/**
 * The proxy class controlling access to the CourseBatch.
 */
public open class ProxyBatch(private val batch: Batch) : Batch {
    override fun totalStudents(): Int {
        return batch.totalStudents()
    }

    override fun registerStudent(name: String) {
        if (MAX_STUDENTS >= totalStudents()) {
            batch.registerStudent(name)
        } else {
            println("Course batch size is : $MAX_STUDENTS")
            println("Max capacity for the Batch filled")
        }
    }

    companion object {
        private const val MAX_STUDENTS = 10
    }

}

fun main() {

    val courseBatch: Batch = CourseBatch()
    val proxyBatch: Batch = ProxyBatch(courseBatch)
    proxyBatch.registerStudent("Alex")
    proxyBatch.registerStudent("John")
    proxyBatch.registerStudent("Marina")
    proxyBatch.registerStudent("Rana")
    proxyBatch.registerStudent("Collin")
    proxyBatch.registerStudent("David")

}