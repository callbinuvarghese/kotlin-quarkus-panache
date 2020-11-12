package com.binu.quark.kotlin.repository

/*https://github.com/arolfes/quarkus-testing-kotlin/blob/master/quarkus-jpa-kotlin/src/test/kotlin/jpa/books/BookRepositoryWithPostgresTest.kt */
class BookRepositoryPGContainerTest {
    /*
    companion object {
        @Container
        private val db = PostgreSQLContainer<Nothing>("postgres").apply {
            withDatabaseName("quarkusjdbc")
            withUsername("quarkus")
            withPassword("changeme")
        }

        @BeforeAll
        @JvmStatic
        fun setProps() {
            db.waitingFor(HostPortWaitStrategy())
            db.start()
            System.setProperty("%test.quarkus.datasource.driver", db.driverClassName)
            System.clearProperty("%test.quarkus.hibernate-orm.dialect")
            System.setProperty("%test.quarkus.datasource.url", db.jdbcUrl)
            System.setProperty("%test.quarkus.datasource.username", db.username)
            System.setProperty("%test.quarkus.datasource.password", db.password)
        }

        @AfterAll
        @JvmStatic
        fun removeProps() {
            System.setProperty("%test.quarkus.datasource.driver", "org.testcontainers.jdbc.ContainerDatabaseDriver")
            System.setProperty("%test.quarkus.hibernate-orm.dialect", "org.hibernate.dialect.PostgreSQL10Dialect")
            System.setProperty("%test.quarkus.datasource.url", "jdbc:tc:postgresql:latest:///dbname")
            System.clearProperty("%test.quarkus.datasource.username")
            System.clearProperty("%test.quarkus.datasource.password")
            if (db.isCreated) {
                db.close()
            }
        }
    }
     */
}