package seerbit.backend.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seerbit.backend.services.payment.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initialize")
    public ResponseEntity<String> initializePayment(@RequestParam String merchantPrivateKey, @RequestParam String merchantPublicKey) {
        String bearerToken = paymentService.generateBearerToken(merchantPrivateKey, merchantPublicKey);

        String checkoutLink = generateCheckoutLink(bearerToken);
        return ResponseEntity.ok("Checkout link: " + checkoutLink);
    }

    private String generateCheckoutLink(String bearerToken) {
        return "https://example.com/checkout?token=" + bearerToken;
    }
}
