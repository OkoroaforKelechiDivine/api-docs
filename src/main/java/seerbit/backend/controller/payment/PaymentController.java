package seerbit.backend.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seerbit.backend.services.payment.PaymentService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initialize")
    public ResponseEntity<String> initializePayment(@RequestParam String merchantPublicKey, @RequestParam BigDecimal amount, @RequestParam String currency,
                                                    @RequestParam String country, @RequestParam String paymentReference, @RequestParam String email,
                                                    @RequestParam(required = false) String productId, @RequestParam(required = false) String productDescription, @RequestParam String callbackUrl) {
        String redirectLink = paymentService.generatePaymentLink(merchantPublicKey, amount, currency, country, paymentReference, email, productId, productDescription, callbackUrl);
        return ResponseEntity.ok("Redirect link: " + redirectLink);
    }
}
