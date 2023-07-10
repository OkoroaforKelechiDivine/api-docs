package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionData {

    private String code;

    private List<MerchantSubscription> subscriptions;


    private Payments payments;

    private String message;
}
