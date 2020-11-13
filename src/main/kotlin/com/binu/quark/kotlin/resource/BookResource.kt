package com.binu.quark.kotlin.resource

import com.binu.quark.kotlin.domain.Book
import com.binu.quark.kotlin.repository.BookRepository
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Timed
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BookResource(val repository: BookRepository) {
    @POST
    @Transactional
    @Timed(name = "add", unit = MetricUnits.MILLISECONDS)
    fun add(book: Book): Response {
        repository.persist(book)
        return Response.ok(book).status(201).build()
    }

    @GET
    @Timed(name = "findAll", unit = MetricUnits.MILLISECONDS)
    fun findAll(): List<Book> = repository.listAll()

}