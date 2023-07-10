package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantSubscription {

    private String publicKey;

    private String amount;

    private String country;

    private String customerId;

    private String cardName;

    private String cardNumber;

    private String plan;

    private String status;

    private String billingId;

    private String authorizationCode;

    private String startDate;

    private long createdAt;
}
