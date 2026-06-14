package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.payload.OrderDTO;
import com.juliodev.ecommerce.payload.OrderRequestDTO;
import com.juliodev.ecommerce.service.OrderService;
import com.juliodev.ecommerce.util.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Order API", description = "Order management endpoints for apply purchases in the e-commerce app")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderDTO>orderProducts(@PathVariable String paymentMethod,
                                                 @RequestBody OrderRequestDTO orderRequestDTO){
        String emailId = this.authUtil.loggedInEmail();
        System.out.println("orderRequestDTO DATA: " + orderRequestDTO);
        OrderDTO order = orderService.placeOrder(
                emailId,
                orderRequestDTO.getAddressId(),
                paymentMethod,
                orderRequestDTO.getPgName(),
                orderRequestDTO.getPgPaymentId(),
                orderRequestDTO.getPgStatus(),
                orderRequestDTO.getPgResponseMessage()
        );

        return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
    }
}
