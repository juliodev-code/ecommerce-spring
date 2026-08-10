package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.payload.OrderDTO;
import com.juliodev.ecommerce.payload.OrderResponse;

public interface OrderService {
    OrderDTO placeOrder(String emailId,
                        Long addressId,
                        String paymentMethod,
                        String pgName,
                        String pgPaymentId,
                        String pgStatus, String pgResponseMessage);

    OrderResponse getAllOrders(Integer pageNumber,
                               Integer pageSize,
                               String sortBy,
                               String sortOrder);

    OrderDTO updateOrder(Long orderId, String status);
}
