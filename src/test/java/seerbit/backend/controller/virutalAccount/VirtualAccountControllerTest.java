package seerbit.backend.controller.virutalAccount;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import seerbit.backend.models.dto.VirtualAccountData;
import seerbit.backend.models.dto.VirtualAccountResponse;
import seerbit.backend.models.virtualAccount.VirtualAccount;
import seerbit.backend.models.virtualAccount.VirtualAccountPayment;
import seerbit.backend.services.virtualAccount.VirtualAccountService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VirtualAccountController.class)
public class VirtualAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VirtualAccountService virtualAccountService;

    @Test
    public void test_createVirtualAccount() throws Exception {
        VirtualAccount request = new VirtualAccount();
        request.setEmail("okoroaforkelechi123@gmail.com");

        VirtualAccountPayment payment = new VirtualAccountPayment();
        payment.setAccountNumber("123456");
        payment.setReference("reference");
        payment.setBankName("bankme");
        payment.setWalletName("walletName");

        VirtualAccountData data =  new VirtualAccountData();
        data.setCode("123");
        data.setPayments(payment);
        data.setMessage("You virtual account has been created successfully");

        VirtualAccountResponse response = new VirtualAccountResponse();
        response.setStatus("Success");
        response.setData(data);

        given(virtualAccountService.createVirtualAccount(any(VirtualAccount.class))).willReturn(response);

        mockMvc.perform(post("/createVirtualAccount")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("request", request))
                .andExpect(status().isOk())
                .andExpect(view().name("virtualAccountResult"))
                .andExpect(model().attribute("response", response));
    }

    @Test
    public void test_getVirtualAccount() throws Exception {
        String paymentReference = "VA_1";

        VirtualAccountData data = new VirtualAccountData();
        data.setPaymentReference(paymentReference);

        VirtualAccountResponse virtualAccountResponse = new VirtualAccountResponse();
        virtualAccountResponse.setStatus("SUCCESS");
        virtualAccountResponse.setData(data);

        Mockito.when(virtualAccountService.getVirtualAccount(paymentReference)).thenReturn(virtualAccountResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/virtual-accounts/{paymenteference}", paymentReference))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reference", Matchers.is(paymentReference)));
    }
}
