package composite

import java.util.*


interface Employee {
    fun showEmployeeDetails()
}

public open class Developer(empID: Long, empName: String, position: String) : Employee {

    private var name: String = empName
    private var empId = empID
    private var position = position

    override fun showEmployeeDetails() {
        println("$empId $name")
    }
}


class Manager(private val empId: Long, private val name: String, private val position: String) : Employee {
    override fun showEmployeeDetails() {
        println("$empId $name")
    }

}

class CompanyDirectory : Employee {
    private val employeeList: MutableList<Employee> = ArrayList()
    override fun showEmployeeDetails() {
        for (emp in employeeList) {
            emp.showEmployeeDetails()
        }
    }

    fun addEmployee(emp: Employee) {
        employeeList.add(emp)
    }

    fun removeEmployee(emp: Employee?) {
        employeeList.remove(emp)
    }
}

fun main() {
    val dev1 = Developer(100, "Katye", " Kotlin Developer")
    val dev2 = Developer(101, "seise", "Kernel Developer")

    val engDirectory = CompanyDirectory()
    engDirectory.addEmployee(dev1)
    engDirectory.addEmployee(dev2)

    val man1 = Manager(200, "Kushagra Garg", "SEO Manager")
    val man2 = Manager(201, "Vikram Sharma ", "Kushagra's Manager")

    val accDirectory = CompanyDirectory()
    accDirectory.addEmployee(man1)
    accDirectory.addEmployee(man2)

    val directory = CompanyDirectory()
    directory.addEmployee(engDirectory)
    directory.addEmployee(accDirectory)
    directory.showEmployeeDetails()
}
