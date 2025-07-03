package com.blackfriday.order.controller

import com.blackfriday.order.dto.FinalOrderDto
import com.blackfriday.order.dto.ProductOrderDetailDto
import com.blackfriday.order.dto.StartOrderDto
import com.blackfriday.order.dto.StartOrderResponseDto
import com.blackfriday.order.entity.ProductOrder
import com.blackfriday.order.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/order/start-order")
    fun startOrder(@RequestBody dto: StartOrderDto): StartOrderResponseDto {
        return orderService.startOrder(dto.userId, dto.productId, dto.count)
    }

    @PostMapping("/order/finish-order")
    fun finishOrder(@RequestBody dto: FinalOrderDto): ProductOrder {
        return orderService.finishOrder(dto.orderId, dto.paymentMethodId, dto.addressId)
    }

    @GetMapping("/order/users/{userId}/orders")
    fun getUserOrders(@PathVariable userId: Long): List<ProductOrder> {
        return orderService.getUserOrders(userId)
    }

    @GetMapping("/order/orders/{orderId}")
    fun getOrder(@PathVariable orderId: Long): ProductOrderDetailDto {
        return orderService.getOrderDetail(orderId)
    }
}