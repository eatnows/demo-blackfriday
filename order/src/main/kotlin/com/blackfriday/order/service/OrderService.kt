package com.blackfriday.order.service

import com.blackfriday.order.dto.DecreaseStockCountDto
import com.blackfriday.order.dto.ProcessDeliveryDto
import com.blackfriday.order.dto.ProcessPaymentDto
import com.blackfriday.order.dto.ProductOrderDetailDto
import com.blackfriday.order.dto.StartOrderResponseDto
import com.blackfriday.order.entity.ProductOrder
import com.blackfriday.order.enums.OrderStatus
import com.blackfriday.order.feign.CatalogClient
import com.blackfriday.order.feign.DeliveryClient
import com.blackfriday.order.feign.PaymentClient
import com.blackfriday.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val paymentClient: PaymentClient,
    private val deliveryClient: DeliveryClient,
    private val catalogClient: CatalogClient,
) {

    fun startOrder(userId: Long, productId: Long, count: Long): StartOrderResponseDto {
        // 1. 상품 정보 조회
        val product = catalogClient.getProduct(productId)

        // 2. 결제 수단 정보 조회
        val paymentMethod = paymentClient.getPaymentMethod(userId)

        // 3. 배송지 정보 조회
        val address = deliveryClient.getUserAddress(userId)

        // 4. 주문 정보 생성
        val order = ProductOrder(
            userId,
            productId,
            count,
            OrderStatus.INITIATED,
            null,
            null
        )

        orderRepository.save(order)

        val startOrderDto = StartOrderResponseDto(
            order.id!!,
            paymentMethod,
            address,
        )

        return startOrderDto
    }

    fun finishOrder(orderId: Long, paymentMethodId: Long, addressId: Long): ProductOrder {
        val order = orderRepository.findById(orderId).orElseThrow()

        // 1. 상품 정보 조회
        val product = catalogClient.getProduct(order.productId)

        // 2. 결제
        val processPaymentDto = ProcessPaymentDto(
            order.id!!,
            order.userId,
            product["price"].toString().toLong() * order.count,
            paymentMethodId,
        )

        val payment = paymentClient.processPayment(processPaymentDto)

        // 3. 배송 요청
        val address = deliveryClient.getAddress(addressId)

        val processDeliveryDto = ProcessDeliveryDto(
            order.id,
            product["name"].toString(),
            order.count,
            address["address"].toString()
        )

        val delivery = deliveryClient.processDelivery(processDeliveryDto)

        // 4. 상품 재고 감소
        val decreaseStockCountDto = DecreaseStockCountDto(
            order.count
        )

        catalogClient.decreaseStockCount(order.productId, decreaseStockCountDto)

        // 5. 주문 정보 업데이트
        order.paymentId = payment["id"].toString().toLong()
        order.deliveryId = delivery["id"].toString().toLong()
        order.orderStatus = OrderStatus.DELIVERY_REQUESTED

        return orderRepository.save(order)
    }

    fun getUserOrders(userId: Long): List<ProductOrder> {
        return orderRepository.findAllByUserId(userId)
    }

    fun getOrderDetail(orderId: Long): ProductOrderDetailDto {
        val order = orderRepository.findById(orderId).orElseThrow()

        val paymentRes = paymentClient.getPayment(order.paymentId!!)
        val deliveryRes = deliveryClient.getDelivery(order.deliveryId!!)

        val dto = ProductOrderDetailDto(
            order.id!!,
            order.userId,
            order.productId,
            order.paymentId!!,
            order.deliveryId!!,
            order.orderStatus,
            paymentRes["paymentStatus"].toString(),
            deliveryRes["status"].toString(),
        )

        return dto
    }
}