package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.payload.StripePaymentDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface StripeService {
    PaymentIntent paymentIntent(StripePaymentDTO stripePaymentDto) throws StripeException;
}
