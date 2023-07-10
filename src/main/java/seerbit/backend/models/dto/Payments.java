package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {

    private String paymentReference;

    private String linkingReference;

    private String redirectUrl;

    private String amount;

    private String currency;

    private String country;

    private String email;

    private String productDescription;


}
