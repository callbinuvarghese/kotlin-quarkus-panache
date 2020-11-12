package com.binu.quark.kotlin.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import com.binu.quark.kotlin.domain.Status
import com.binu.quark.kotlin.domain.Todo
import java.time.LocalDate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TodoRepository : PanacheRepository<Todo> {
    fun findByStatus(status: Status): List<Todo> = list("status", status)
    fun findByDateComplete(date: LocalDate): List<Todo> = list("completeBy > ?1", date)
}