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
    public VirtualAccountResponse createVirtualAccount(VirtualAccount request) {
        String apiUrl = "https://seerbitapi.com/api/v2/virtual-accounts/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer YOUR_AUTH_TOKEN");

        HttpEntity<VirtualAccount> httpEntity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<VirtualAccountResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, VirtualAccountResponse.class);
        return responseEntity.getBody();
    }
}