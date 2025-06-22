package com.eatnows.demoblackfriday.cassandra.entity

import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn

@PrimaryKeyClass
data class EmployeePrimaryKey(
    @PrimaryKeyColumn(name = "location", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    val location: String,

    @PrimaryKeyColumn(name = "department", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    val department: String,

    @PrimaryKeyColumn(name = "name", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    val name: String
)