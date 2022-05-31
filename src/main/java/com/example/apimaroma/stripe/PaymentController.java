package com.example.apimaroma.stripe;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    public  PaymentController(){
        Stripe.apiKey = "sk_test_51L5Ss4CdBTMuH0DTuBu403te6N8BEQRy66T6hkb81d5aWyFqiINHc12qTmzaCfum6APj6loT85bCKJEJmF439PUy001xwb0i1h";

    }


    @PostMapping("/createPaymentIntent")
    public CreatePaymentResponse createPaymentIntent(CreatePayment createPayment) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setCurrency("eur")
                        .setAmount(10*100L)
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();
    PaymentIntent intent = PaymentIntent.create(params);
    return new CreatePaymentResponse(intent.getClientSecret());


    }
}
