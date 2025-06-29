package com.blackfriday.search.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    // key -> value
    // tag(keyword) -> 1, 2, 3, 4, 5, 6 (Set)

    fun addTagCache(productId: Long, tags: List<String>) {
        val ops = stringRedisTemplate.opsForSet()

        tags.forEach {
            ops.add(it, productId.toString())
        }
    }

    fun removeTagCache(productId: Long, tags: List<String>) {
        val ops = stringRedisTemplate.opsForSet()

        tags.forEach {
            ops.remove(it, productId.toString())
        }
    }

    fun getProductIdsByTag(tag: String): List<Long> {
        val ops = stringRedisTemplate.opsForSet()
        val values = ops.members(tag)
            ?: return emptyList()
        return values.map { it.toLong() }
    }
}