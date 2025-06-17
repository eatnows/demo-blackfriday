package com.eatnows.demoblackfriday

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun publish() {
        kafkaTemplate.send("topic1", "message sent (topic1)")
    }

    @KafkaListener(topics = ["topic1"], groupId = "testgroup")
    fun consume(message: String) {
        println("??????")
        println("consumed: $message")
    }
}