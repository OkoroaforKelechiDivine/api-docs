package seerbit.backend.services.virtualAccount;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seerbit.backend.models.dto.VirtualAccountResponse;
import seerbit.backend.models.virtualAccount.VirtualAccount;

@Service
public class VirtualAccountService {
    private static final String SEERBIT_API_URL = "https://seerbitapi.com/api/v2/virtual-accounts/";

    public VirtualAccountResponse createVirtualAccount(VirtualAccount request) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer YOUR_AUTH_TOKEN");

        HttpEntity<VirtualAccount> httpEntity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<VirtualAccountResponse> responseEntity = restTemplate.exchange(SEERBIT_API_URL, HttpMethod.POST, httpEntity, VirtualAccountResponse.class);
        return responseEntity.getBody();
    }

    public VirtualAccountResponse getVirtualAccount(String paymentReference) {
        String apiUrl = SEERBIT_API_URL + paymentReference;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<VirtualAccountResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, VirtualAccountResponse.class);
        return response.getBody();
    }
}