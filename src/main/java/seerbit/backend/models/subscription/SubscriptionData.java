package seerbit.backend.models.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seerbit.backend.models.merchant.MerchantSubscription;
import seerbit.backend.models.payments.Payments;

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
