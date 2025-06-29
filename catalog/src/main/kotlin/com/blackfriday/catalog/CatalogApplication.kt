package com.blackfriday.catalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@SpringBootApplication
@EnableCassandraRepositories
class CatalogApplication

fun main(args: Array<String>) {
    runApplication<CatalogApplication>(*args)
}