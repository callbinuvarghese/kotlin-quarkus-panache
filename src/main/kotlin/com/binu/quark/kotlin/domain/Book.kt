package com.binu.quark.kotlin.domain

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

/* Non integer primary key UUID;so PanancheEntityBase instead of PanacheEntity */
@Entity
data class Book(@Id
                 var id: UUID,
                var title: String,
                var isbn: String): PanacheEntityBase()
