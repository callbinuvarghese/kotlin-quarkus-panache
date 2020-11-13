package com.binu.quark.kotlin.resource

import com.binu.quark.kotlin.TransactionalQuarkusTest
import com.binu.quark.kotlin.domain.Book
import com.binu.quark.kotlin.domain.TodayResponse
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.time.format.DateTimeFormatter
import java.util.*



@TransactionalQuarkusTest
class BookResourceTest {
    var checkStatusCodeAndContentType = ResponseSpecBuilder().
                                        expectStatusCode(200).
                                        expectContentType(ContentType.JSON)
                                        .build()

    @Test
    fun getBooks() {
        // expected
        val e1 = Book(UUID.randomUUID(), "Clean Code", "9780132350884")
        val e2 = Book(UUID.randomUUID(), "Clean Architecture", "9780134494166")
        val e3 =Book(UUID.randomUUID(), "Clean Code", "9780132350884")

        Given{
            //baseUri("http://localhost")
            //basePath("/path")
           // header("x-trace-id", UUID.randomUUID().toString())
            header("Content-Type", ContentType.JSON)
            body(e1)
            //log().all()
        } When { post("/books")
        } Then {
            statusCode(201)
            //body(hasXPath("book/title[text()='Clean Code']"))
            body(containsString("Clean Code"))
            log()
        } Extract {
            // First get the JsonPath object instance from the Response interface
            val result: Book = response().`as`(Book::class.java)
            assertThat(result.title, containsString(e1.title))
        }

/*        val widgets = get("/widget")
                .then()
                .statusCode(200)
                .extract().to<List<Widget>>()

        assertThat(widgets).containsOnly(widget1, widget2)
*/
    }

    @Test
    fun responseClassWithExternalCall() {
        //Response expected
        //{ "date": "11-13-2020", "milliseconds_since_epoch": 1605237393247, "time": "03:16:33 AM" }
        //we have to check UTC time as the server return UTC time
        val todayResponseExpected = TodayResponse();
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        val yearExpectedString: String = todayResponseExpected.date.format(formatter)

       Given {
           header("Content-Type", ContentType.JSON)
           log().all()
           port(80)
       } When {
           get("http://date.jsontest.com")
       } Then {
           spec(checkStatusCodeAndContentType)
           body("date", equalTo(todayResponseExpected.date)) //redundant checking; just to give 2 flavors of assert
       } Extract {
            val result: TodayResponse = response().`as`(TodayResponse::class.java)
            assertThat(result.date, equalTo(todayResponseExpected.date)) //body check is duplicate - only one form is necessary
       }
    }
}