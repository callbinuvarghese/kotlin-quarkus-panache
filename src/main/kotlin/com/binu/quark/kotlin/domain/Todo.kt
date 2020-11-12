package com.binu.quark.kotlin.domain

import io.quarkus.hibernate.orm.panache.PanacheEntity
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "todo")
data class Todo ( @field:Column(name ="name", length = 32, nullable = false, unique = true)  var task: String = "",
                 @field:Column(name ="description", length = 255, nullable = false) var desc: String = "",
                 var hours: Int = 0,
                 @field:Column(name = "compdate") var completeBy: LocalDate? = null,
                 var status: Status? = null): PanacheEntity()