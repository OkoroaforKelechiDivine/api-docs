package seerbit.backend.controller.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import seerbit.backend.models.merchant.MerchantSubscription;
import seerbit.backend.models.payments.Payments;
import seerbit.backend.models.subscription.SubscriptionData;
import seerbit.backend.models.subscription.SubscriptionRequest;
import seerbit.backend.models.subscription.SubscriptionResponse;
import seerbit.backend.services.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class SubscriptionControllerTest {

    @Mock
    private RestTemplate restTemplate;

    private SubscriptionService subscriptionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        subscriptionService = new SubscriptionService(restTemplate);
    }

    @Test
    public void test_createSubscriptionAndShouldReturnResponse() {
        String url = "https://seerbitapi.com/api/v2/recurring/subscribes";
        SubscriptionRequest request = createMockSubscriptionRequest();
        SubscriptionResponse expectedResponse = createMockSubscriptionResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SubscriptionRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SubscriptionResponse> responseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.postForEntity(url, entity, SubscriptionResponse.class)).thenReturn(responseEntity);
        SubscriptionResponse actualResponse = subscriptionService.createSubscription(request);
        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).postForEntity(url, entity, SubscriptionResponse.class);
    }

    @Test
    public void test_getMerchantSubscriptionsAndShouldReturnResponse() {
        String publicKey = "SBPUBK_DQ24K6T5TI1WOAOYPWWYMGMHKDRVEGPW";
        String url = "https://seerbitapi.com/api/v2/recurring/publicKey/{publicKey}";
        SubscriptionResponse expectedResponse = createMockSubscriptionResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<SubscriptionResponse> responseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.postForEntity(url, entity, SubscriptionResponse.class, publicKey)).thenReturn(responseEntity);
        SubscriptionResponse actualResponse = subscriptionService.getMerchantSubscriptions(publicKey);
        verify(restTemplate, times(1)).postForEntity(url, entity, SubscriptionResponse.class, publicKey);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void test_getCustomerSubscriptionAndShouldReturnResponse() {
        String publicKey = "SBPUBK_DQ24K6T5TI1WOAOYPWWYMGMHKDRVEGPW";
        String customerId = "ba981a0b7ed1c68ad245";
        String url = "https://seerbitapi.com/api/v2/recurring/" + publicKey + "/customerId/" + customerId;
        SubscriptionResponse expectedResponse = createMockSubscriptionResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<SubscriptionResponse> responseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.postForEntity(url, entity, SubscriptionResponse.class)).thenReturn(responseEntity);
        SubscriptionResponse actualResponse = subscriptionService.getCustomerSubscription(publicKey, customerId);
        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).postForEntity(url, entity, SubscriptionResponse.class);
    }

    @Test
    public void test_updateSubscriptionAndShouldReturnResponse() {
        String url = "https://seerbitapi.com/eactrecurrent/updates";
        SubscriptionRequest request = createMockSubscriptionRequest();
        SubscriptionResponse expectedResponse = createMockSubscriptionResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SubscriptionRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SubscriptionResponse> responseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.postForEntity(url, entity, SubscriptionResponse.class)).thenReturn(responseEntity);
        SubscriptionResponse actualResponse = subscriptionService.updateCustomerSubscription(request);
        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).postForEntity(url, entity, SubscriptionResponse.class);
    }
    private SubscriptionRequest createMockSubscriptionRequest() {
        SubscriptionRequest request = new SubscriptionRequest();
        request.setAmount("200");
        request.setPublicKey("publicKey");
        request.setEmail("js@emaildomain.com");
        request.setAllowPartialDebit("true");
        request.setAuthorizationCode("1234567898765325");
        request.setPaymentReference("2938765582R37065687631");
        request.setCurrency("NGN");
        return request;
    }

    private SubscriptionResponse createMockSubscriptionResponse() {
        SubscriptionResponse response = new SubscriptionResponse();
        response.setStatus("SUCCESS");

        SubscriptionData data = new SubscriptionData();
        List<MerchantSubscription> subscriptions = new ArrayList<>();
        data.setCode("S20");

        Payments payments = new Payments();
        payments.setPaymentReference("WQ6676yPOpr12348o");
        payments.setLinkingReference("F194900041578648252258");
        payments.setRedirectUrl("https://staging.seerbitapigateway.com/seerbit/card-dispatch?sredref=F194900041578648252258");

        payments.setAmount("200");
        payments.setCurrency("NGN");
        payments.setCountry("NG");
        payments.setEmail("js@emaildomain.com");
        payments.setProductDescription("Authorised charge");

        data.setPayments(payments);
        data.setMessage("Transaction is pending");

        MerchantSubscription subscription1 = new MerchantSubscription();
        subscription1.setPublicKey("SBPUBK_DQ24K6T5TI1WOAOYPWWYMGMHKDRVEGPW");
        subscription1.setAmount("20");
        subscription1.setCountry("NG");
        subscription1.setCustomerId("651d33a62ad69c9f37c4");
        subscription1.setCardName("Jane Smith");
        subscription1.setCardNumber("2223-00xx-xxxx-0007");
        subscription1.setPlan("ae702f51220000722dca");
        subscription1.setStatus("ACTIVE");
        subscription1.setBillingId("WQ6676yPOpr12348o");
        subscription1.setAuthorizationCode("2beb0ccdd347e604552a");
        subscription1.setStartDate("2019-01-11 00:00:00");
        subscription1.setCreatedAt(1578648329000L);

        MerchantSubscription subscription2 = new MerchantSubscription();
        subscription2.setPublicKey("SBPUBK_DQ24K6T5TI1WOAOYPWWYMGMHKDRVEGPW");
        subscription2.setAmount("100");
        subscription2.setCountry("NG");
        subscription2.setCustomerId("ba981a0b7ed1c68ad245");
        subscription2.setCardName("Jane Smith");
        subscription2.setCardNumber("5123-45xx-xxxx-0008");
        subscription2.setPlan("ead5e697f42c1cd60813");
        subscription2.setStatus("ACTIVE");
        subscription2.setBillingId("PUBK_PjQ5d1578649732262");
        subscription2.setAuthorizationCode("145a3bb3418824c14d65");
        subscription2.setStartDate("2020-10-01 10:47:49");
        subscription2.setCreatedAt(1578649752000L);

        subscriptions.add(subscription1);
        subscriptions.add(subscription2);

        data.setSubscriptions(subscriptions);
        data.setCode("00");
        data.setMessage("successful");
        response.setData(data);
        return response;
    }
}