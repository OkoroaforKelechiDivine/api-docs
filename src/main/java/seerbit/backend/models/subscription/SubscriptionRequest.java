package seerbit.backend.models.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {

    private String publicKey;

    private String paymentReference;

    private String planId;

    private String customerId;

    private String cardNumber;

    private String expiryMonth;

    private String redirectUrl;

    private String expiryYear;

    private String cvv;

    private String amount;

    private String currency;

    private String productDescription;

    private String productId;

    private String country;

    private String startDate;

    private String cardName;

    private String billingCycle;

    private String allowPartialDebit;

    private String email;

    private String authorizationCode;

    private String mobileNumber;

    private String billingPeriod;

    private Boolean subscriptionAmount;
}
