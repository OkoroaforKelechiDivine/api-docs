package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SubscriptionResponse {

    private String status;

    private SubscriptionData data;

    public SubscriptionResponse() {
        this.data = new SubscriptionData();
    }
}
