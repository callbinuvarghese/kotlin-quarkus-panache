package com.binu.quark.kotlin.repository

import com.binu.quark.kotlin.domain.Book
import io.quarkus.test.junit.QuarkusTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.transaction.Transactional

@TransactionalQuarkusTest
class BookRepositoryTest() {

    @Inject
    @field: Default
    lateinit var bookRepository: BookRepository

    @BeforeEach
    fun clearDatabase() { bookRepository.deleteAll()}

    @Test
    fun `entity can persist  and found by id`() {
        val id = UUID.randomUUID();
        val entity = Book(id, "Clean Code", "9780132350884")
        bookRepository.persist(entity)
        val foundEntity = bookRepository.findById(id)
        assertThat(foundEntity, equalTo(entity))
    }

    @Test
    fun `entity can be found by title`() {

        val e1 = Book(UUID.randomUUID(), "Clean Code", "9780132350884")
        bookRepository.persist(e1)
        val e2 = Book(UUID.randomUUID(), "Clean Architecture", "9780134494166")
        bookRepository.persist(e2)
        val e3 =Book(UUID.randomUUID(), "Clean Code", "9780132350884")
        bookRepository.persist(e3)


        val foundEntities = bookRepository.findByTitle("Clean Code")
        assertThat(foundEntities, containsInAnyOrder(e1, e3))
        assertThat(foundEntities, not(contains(e2)))
    }

}
