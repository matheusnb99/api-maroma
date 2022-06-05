package com.example.apimaroma.stripe;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
@RequestMapping(path = "api/v1/payment")

public class PaymentController {
    public PaymentController() {
        Stripe.apiKey = "sk_test_51L7KkTDHIGzYU7TgvoQf3Qek4vpk54qguW5MTQuNxrd8Y1ccGy0xiofM4oo8F8e5LgwIkYbP1kgRH2PuEjkaKJwB0031fHZgr2";
    }

    @PostMapping("/createPaymentIntent")
    public String createPaymentIntent(@RequestBody HashMap<String, Object> bodyMap) throws StripeException {
        Gson gson = new Gson();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCurrency("eur")
                .setAmount((Integer) bodyMap.get("amount") * 100L)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());

        System.out.println(gson.toJson(paymentResponse));
        return gson.toJson(paymentResponse);
    }
}
