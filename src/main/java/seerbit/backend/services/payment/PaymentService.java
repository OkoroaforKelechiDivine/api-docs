package seerbit.backend.services.payment;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seerbit.backend.models.dto.SeerbitResponse;
import seerbit.backend.models.payments.PaymentRequest;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private static final String ENCRYPT_KEYS_URL = "https://seerbitapi.com/api/v2/encrypt/keys";
    private static final String PAYMENTS_URL = "https://seerbitapi.com/api/v2/payments";


    public String generateBearerToken(String merchantPrivateKey, String merchantPublicKey) {
        String keys = merchantPrivateKey + "." + merchantPublicKey;
        PaymentRequest request = new PaymentRequest();
        request.setKey(keys);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<SeerbitResponse> response = restTemplate.exchange(
                ENCRYPT_KEYS_URL,
                HttpMethod.POST,
                entity,
                SeerbitResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            SeerbitResponse seerBitResponse = response.getBody();
            if (seerBitResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                return seerBitResponse.getData().getEncryptedSecKey().getEncryptedKey();
            } else {
                throw new RuntimeException("Failed to generate bearer token: " + seerBitResponse.getMessage());
            }
        } else {
            throw new RuntimeException("Failed to generate bearer token");
        }
    }

    public String generatePaymentLink(String merchantPublicKey, BigDecimal amount, String currency, String country, String paymentReference,
                                      String email, String productId, String productDescription, String callbackUrl) {
        PaymentRequest request = new PaymentRequest();
        request.setKey(merchantPublicKey);
        request.setAmount(amount);
        request.setCurrency(currency);
        request.setCountry(country);
        request.setPaymentReference(paymentReference);
        request.setEmail(email);
        request.setProductId(productId);
        request.setProductDescription(productDescription);
        request.setCallbackUrl(callbackUrl);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<SeerbitResponse> response = restTemplate.exchange(
                PAYMENTS_URL, HttpMethod.POST, entity, SeerbitResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            SeerbitResponse paymentResponse = response.getBody();
            if (paymentResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                return paymentResponse.getData().getPayments().getRedirectUrl();
            } else {
                throw new RuntimeException("Failed to generate payment link: " + paymentResponse.getMessage());
            }
        } else {
            throw new RuntimeException("Failed to generate payment link");
        }
    }
}
