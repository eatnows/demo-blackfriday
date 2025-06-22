package com.eatnows.demoblackfriday

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val kafkaService: KafkaService,
    private val cassandraService: CassandraService,
) {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @GetMapping("/kafka-test")
    fun kafkaTest() {
        kafkaService.publish()
    }

    @GetMapping("/cas-test")
    fun cassandraTest() {
        cassandraService.casTest();
    }
}