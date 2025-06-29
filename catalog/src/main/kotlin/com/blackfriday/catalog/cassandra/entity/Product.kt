package com.blackfriday.catalog.cassandra.entity

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table
data class Product(
    @PrimaryKey
    val id: Long,

    @Column
    val sellerId: Long,

    @Column
    val name: String,

    @Column
    val description: String,

    @Column
    val price: Long,

    @Column
    var stockCount: Long,

    @Column
    val tags: List<String>,
)