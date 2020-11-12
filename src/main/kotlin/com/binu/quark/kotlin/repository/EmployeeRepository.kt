package com.binu.quark.kotlin.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import com.binu.quark.kotlin.domain.Employee
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class EmployeeRepository: PanacheRepository<Employee> {
    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Employee> =
            list("firstName = ?1 and lastName = ?2", firstName, lastName)

    fun findBySalary(salary: Int): List<Employee> = list("salary", salary)

    fun findBySalaryGreaterThan(salary: Int): List<Employee> = list("salary > ?1", salary)
}