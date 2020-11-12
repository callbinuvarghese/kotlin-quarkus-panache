package com.binu.quark.kotlin.repository

import com.binu.quark.kotlin.domain.Book
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BookRepository: PanacheRepositoryBase<Book, UUID> {
    fun findByTitle(title: String): List<Book> = find("title", title).list()
}