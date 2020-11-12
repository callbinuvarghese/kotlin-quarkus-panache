package com.binu.quark.kotlin.domain

import io.quarkus.hibernate.orm.panache.PanacheEntity
import javax.persistence.Entity

@Entity
data class Employee(var firstName: String = "",
                    var lastName: String = "",
                    var position: String = "",
                    var salary: Int = 0,
                    var organizationId: Int? = null,
                    var departmentId: Int? = null): PanacheEntity()