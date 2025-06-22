package com.eatnows.demoblackfriday

import com.eatnows.demoblackfriday.cassandra.entity.Employee
import com.eatnows.demoblackfriday.cassandra.entity.EmployeePrimaryKey
import com.eatnows.demoblackfriday.cassandra.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class CassandraService(
    private val employeeRepository: EmployeeRepository,
) {
    fun casTest() {
        val employee1 = Employee(
            EmployeePrimaryKey(
                "seoul",
                "business",
                "key"
            ),
            "010-1234-5678"
        )

        employeeRepository.save(employee1)

        val employee2 = Employee(
            EmployeePrimaryKey(
                "seoul",
                "business",
                "joy"
            ),
            "010-3333-3333"
        )

        employeeRepository.save(employee2)

        val result =
            employeeRepository.findByKeyLocationAndKeyDepartment("seoul", "business")

        result.forEach {
            println("==== DEBUG ====")
            println("location: ${it.key?.location} (length=${it.key?.location?.length})")
            println("department: ${it.key?.department} (length=${it.key?.department?.length})")
            println("name: ${it.key?.name} (length=${it.key?.name?.length})")
            println("phoneNumber: ${it.phoneNumber} (length=${it.phoneNumber.length})")
        }

    }

}
