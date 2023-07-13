package seerbit.backend.models.token;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTokenRequest {

    private String publicKey;

    private String amount;

    private String fullName;

    private String mobileNumber;

    private String redirectUrl;

    private String currency;

    private String country;

    private String paymentReference;

    private String email;

    private String paymentType;

    private String cardNumber;

    private String expiryMonth;

    private String expiryYear;

    private String cvv;

    private String pin;
}