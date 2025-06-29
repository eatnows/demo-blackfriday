package com.blackfriday.catalog.cassandra.repository

import com.blackfriday.catalog.cassandra.entity.Product
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CassandraRepository<Product, Long>{
}