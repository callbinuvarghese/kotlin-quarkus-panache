package com.binu.quark.kotlin.resource

import com.binu.quark.kotlin.domain.Status
import com.binu.quark.kotlin.domain.Todo
import com.binu.quark.kotlin.repository.TodoRepository
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Timed
import java.time.LocalDate
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TodoResource(val repository: TodoRepository) {
    @POST
    @Transactional
    @Timed(name = "add", unit = MetricUnits.MILLISECONDS)
    fun add(todo: Todo): Response {
        repository.persist(todo)
        return Response.ok(todo).status(201).build()
    }

    @GET
    @Timed(name = "findAll", unit = MetricUnits.MILLISECONDS)
    fun findAll(): List<Todo> = repository.listAll()

    @GET
    @Path("{id}")
    @Timed(name = "findById", unit = MetricUnits.MILLISECONDS)
    fun findById(@PathParam("id") id: Long): Todo? = repository.findById(id)

    @GET
    @Path("status/{status}")
    @Timed(name = "findByStatus", unit = MetricUnits.MILLISECONDS)
    fun findByStatus(@PathParam("status") status: Status): List<Todo>? = repository.findByStatus(status)


    @GET
    @Path("byDateComplete/{date}")
    @Timed(name = "findByDateComplete", unit = MetricUnits.MILLISECONDS)
    fun findBySalaryGreaterThan(@PathParam("date") date: String): List<Todo>?
            = repository.findByDateComplete(LocalDate.parse(date))



}