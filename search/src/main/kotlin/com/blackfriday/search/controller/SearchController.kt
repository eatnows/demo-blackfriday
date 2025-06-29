package com.blackfriday.search.controller

import com.blackfriday.search.dto.ProductTagsDto
import com.blackfriday.search.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    private val searchService: SearchService,
) {
    @PostMapping("/search/addTagCache")
    fun addTagCache(@RequestBody dto: ProductTagsDto) {
            searchService.addTagCache(dto.productId, dto.tags)
    }

    @PostMapping("/search/removeTagCache")
    fun removeTagCache(@RequestBody dto: ProductTagsDto) {
        searchService.removeTagCache(dto.productId, dto.tags)
    }

    @GetMapping("/search/tags/{tag}/productIds")
    fun getTagProductIds(@PathVariable tag: String): List<Long> {
        return searchService.getProductIdsByTag(tag)
    }
}