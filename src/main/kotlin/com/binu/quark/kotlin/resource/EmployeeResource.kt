package com.binu.quark.kotlin.resource

import com.binu.quark.kotlin.domain.Employee
import com.binu.quark.kotlin.repository.EmployeeRepository
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Timed
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EmployeeResource(val repository: EmployeeRepository) {

    @POST
    @Transactional
    @Timed(name = "add", unit = MetricUnits.MILLISECONDS)
    fun add(employee: Employee): Response {
        repository.persist(employee)
        return Response.ok(employee).status(201).build()
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Timed(name = "delete", unit = MetricUnits.MILLISECONDS)
    fun delete(@PathParam("id") id: Long) {
        repository.deleteById(id)
    }

    @GET
    @Timed(name = "findAll", unit = MetricUnits.MILLISECONDS)
    fun findAll(): List<Employee> = repository.listAll()

    @GET
    @Path("{id}")
    @Timed(name = "findById", unit = MetricUnits.MILLISECONDS)
    fun findById(@PathParam("id") id: Long): Employee? = repository.findById(id)

    @GET
    @Path("first-name/{firstName}/last-name/{lastName}")
    @Timed(name = "findByFirstNameAndLastName", unit = MetricUnits.MILLISECONDS)
    fun findByFirstNameAndLastName(@PathParam("firstName") firstName: String, @PathParam("lastName") lastName: String): List<Employee>
            = repository.findByFirstNameAndLastName(firstName, lastName)

    @GET
    @Path("salary/{salary}")
    @Timed(name = "findBySalary", unit = MetricUnits.MILLISECONDS)
    fun findBySalary(@PathParam("salary") salary: Int): List<Employee> = repository.findBySalary(salary)

    @GET
    @Path("salary-greater-than/{salary}")
    @Timed(name = "findBySalaryGreaterThan", unit = MetricUnits.MILLISECONDS)
    fun findBySalaryGreaterThan(@PathParam("salary") salary: Int): List<Employee>
            = repository.findBySalaryGreaterThan(salary)

    @ConfigProperty(name = "property1")
    lateinit var property1: String

    @GET
    @Path("property1")
    fun property1(): String = property1
}