package seerbit.backend.controller.payment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import seerbit.backend.services.payment.PaymentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void test_initializePayment() throws Exception {
        String merchantPrivateKey = "your-merchant-private-key";
        String merchantPublicKey = "your-merchant-public-key";
        String bearerToken = "generated-bearer-token";
        String checkoutLink = "generated-checkout-link";

        given(paymentService.generateBearerToken(merchantPrivateKey, merchantPublicKey)).willReturn(bearerToken);

        mockMvc.perform(post("/payments/initialize")
                        .param("merchantPrivateKey", merchantPrivateKey)
                        .param("merchantPublicKey", merchantPublicKey))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Checkout link: " + checkoutLink));
    }
}

