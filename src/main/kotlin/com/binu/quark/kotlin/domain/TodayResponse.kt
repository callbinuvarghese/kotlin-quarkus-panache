package com.binu.quark.kotlin.domain

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.Column


class TodayResponse(var time: String, @field:Column(name = "milliseconds_since_epoch") var milliseconds_since_epoch: Long, @field:Column(name = "date") var date: String)
{
    constructor() : this(Date().toInstant().atZone(ZoneId.of("UTC")).toLocalTime().toString(), 0L,DateTimeFormatter.ofPattern("MM-dd-yyyy").format(Date().toInstant().atZone(ZoneId.of("UTC")).toLocalDate()))
}

