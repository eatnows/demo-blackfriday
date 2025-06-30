package com.blackfriday.catalog.feign

import com.blackfriday.catalog.dto.ProductTagDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "searchClient", url = "http://search-service:8080")
interface SearchClient {
    @PostMapping("/search/addTagCache")
    fun addTagCache(@RequestBody dto: ProductTagDto)

    @PostMapping("/search/addTagCache")
    fun removeTagCache(@RequestBody dto: ProductTagDto)
}