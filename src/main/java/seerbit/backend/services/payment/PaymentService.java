package seerbit.backend.services.payment;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seerbit.backend.models.dto.SeerbitResponse;
import seerbit.backend.models.payments.PaymentRequest;

@Service
public class PaymentService {
    private static final String ENCRYPT_KEYS_URL = "https://seerbitapi.com/api/v2/encrypt/keys";

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
}
