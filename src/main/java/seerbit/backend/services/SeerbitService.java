package seerbit.backend.services;
import org.springframework.stereotype.Service;
import seerbit.backend.models.dto.ApiResponse;
import seerbit.backend.models.dto.PaymentRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SeerbitService {
    private static final String API_BASE_URL ="https://checkout.seerbitapi.com/api/v2/seerbit.js";
    private static final String API_PUBLIC_KEY = "SBPUBK_DQ24K6T5TI1WOAOYPWWYMGMHKDRVEGPW";
    public void processPayment(PaymentRequest paymentRequest) {
        Map<String, Object> requestData = preparePaymentRequest(paymentRequest);

        ApiResponse response = sendApiRequest("/payments", requestData);

        assert response != null;
        if (response.isSuccess()) {
            String transactionId = response.getData().get("transaction_id");
        } else {
            String errorCode = response.getError().get("code");
            String errorMessage = response.getError().get("message");
        }
    }

    private Map<String, Object> preparePaymentRequest(PaymentRequest paymentRequest) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("public_key", API_PUBLIC_KEY);
        requestData.put("tranref", paymentRequest.getTransactionReference());
        requestData.put("currency", paymentRequest.getCurrency());
        requestData.put("amount", paymentRequest.getAmount());
        requestData.put("email", paymentRequest.getEmail());
        requestData.put("full_name", paymentRequest.getFullName());

        return requestData;
    }

    private ApiResponse sendApiRequest(String endpoint, Map<String, Object> requestData) {
        // Build the API request URL
        String apiUrl = API_BASE_URL + endpoint;
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_PUBLIC_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(Objects.requireNonNull(JsonUtils.toJson(requestData))))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the API response JSON into ApiResponse object
            ApiResponse response = JsonUtils.fromJson(httpResponse.body(), ApiResponse.class);

            return response;
        } catch (IOException | InterruptedException e) {
            // Handle any exceptions that occurred during the API request
            e.printStackTrace();
            return null;
        }
    }
}