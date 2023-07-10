package seerbit.backend.controller.checkout;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import seerbit.backend.models.subscription.SubscriptionRequest;
import seerbit.backend.models.subscription.SubscriptionResponse;
import seerbit.backend.services.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.createSubscription(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/merchant/{publicKey}")
    public ResponseEntity<SubscriptionResponse> getMerchantSubscriptions(@PathVariable String publicKey) {
        SubscriptionResponse response = subscriptionService.getMerchantSubscriptions(publicKey);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/charge")
    public ResponseEntity<SubscriptionResponse> chargeSubscription(@RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.chargeSubscription(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<SubscriptionResponse> getCustomerSubscription(@RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.getCustomerSubscription(request.getPublicKey(), request.getCustomerId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<SubscriptionResponse> updateCustomerSubscription(@RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.updateCustomerSubscription(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}