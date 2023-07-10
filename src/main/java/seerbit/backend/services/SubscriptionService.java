package seerbit.backend.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seerbit.backend.models.subscription.SubscriptionRequest;
import seerbit.backend.models.subscription.SubscriptionResponse;
@Service
public class SubscriptionService {

    private final RestTemplate restTemplate;

    public SubscriptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        String url = "https://seerbitapi.com/api/v2/recurring/subscribes";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SubscriptionRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SubscriptionResponse> responseEntity = restTemplate.postForEntity(url, entity, SubscriptionResponse.class);
        SubscriptionResponse response = responseEntity.getBody();

        if (response != null && response.getData() != null) {
            response.setStatus("SUCCESS");
            response.getData().setCode("S20");
            response.getData().setMessage("Transaction is pending");
        }
        return response;
    }

    public SubscriptionResponse getMerchantSubscriptions(String publicKey) {
        String url = "https://seerbitapi.com/api/v2/recurring/publicKey/{publicKey}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<SubscriptionResponse> responseEntity = restTemplate.postForEntity(url, entity, SubscriptionResponse.class, publicKey);
        return responseEntity.getBody();
    }

    public SubscriptionResponse chargeSubscription(SubscriptionRequest request) {
        String url = "https://seerbitapi.com/api/v2/recurring/charge";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SubscriptionRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SubscriptionResponse> responseEntity = restTemplate.postForEntity(url, entity, SubscriptionResponse.class);
        return responseEntity.getBody();
    }

    public SubscriptionResponse getCustomerSubscription(String publicKey, String customerId) {
        String url = "https://seerbitapi.com/api/v2/recurring/" + publicKey + "/customerId/" + customerId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<SubscriptionResponse> responseEntity = restTemplate.postForEntity(url, entity, SubscriptionResponse.class);
        return responseEntity.getBody();
    }

    public SubscriptionResponse updateCustomerSubscription(SubscriptionRequest request) {
        String url = "https://seerbitapi.com/eactrecurrent/updates";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SubscriptionRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SubscriptionResponse> responseEntity = restTemplate.postForEntity(url, entity, SubscriptionResponse.class);
        SubscriptionResponse response = responseEntity.getBody();
        return response;
    }
}
