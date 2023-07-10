package seerbit.backend.controller.checkout;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import seerbit.backend.services.SeerbitService;


@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SeerbitService seerbitService;

    @Test
    public void testShowCheckoutPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/checkout"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("checkout"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("transactionReference", "customerEmail"));
    }

    @Test
    public void testPaywithSeerbit() throws Exception {
        Mockito.doNothing().when(seerbitService).processPayment(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/pay"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}