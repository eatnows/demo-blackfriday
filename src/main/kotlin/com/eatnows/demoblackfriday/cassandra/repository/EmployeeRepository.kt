package com.eatnows.demoblackfriday.cassandra.repository

import com.eatnows.demoblackfriday.cassandra.entity.Employee
import com.eatnows.demoblackfriday.cassandra.entity.EmployeePrimaryKey
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CassandraRepository<Employee, EmployeePrimaryKey> {
    fun findByKeyLocationAndKeyDepartment(location: String, department: String): List<Employee>
}