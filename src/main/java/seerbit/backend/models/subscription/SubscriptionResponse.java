package seerbit.backend.models.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import seerbit.backend.models.subscription.SubscriptionData;

@Data
@AllArgsConstructor
public class SubscriptionResponse {

    private String status;

    private SubscriptionData data;

    public SubscriptionResponse() {
        this.data = new SubscriptionData();
    }
}
